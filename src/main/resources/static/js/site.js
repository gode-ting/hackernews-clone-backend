function loginUser(form) {

	var username = document.getElementById('usernameLogin').value;
	var password = document.getElementById('passwordLogin').value;

	var endpoint = 'http://46.101.190.192:8080/login';
	var method = 'POST';
	var body = JSON.stringify({
		username: username,
		password: password
	});

	var request = new Request(endpoint, {
		method: method,
		credentials: 'cors',
		mode: 'CORS',
		redirect: 'follow',
		headers: new Headers({
			'Access-Control-Allow-Origin': 'http://46.101.190.192:8080',
			'Access-Control-Allow-Credentials': true,
			'Access-Control-Expose-Headers': 'Authorization',
			'Access-Control-Allow-Headers': 'Authorization'
		}),
		body: body
	});

	fetch(request).then(function (response) {
		if (!response.ok) {
			document.getElementById('loginError').style.display = 'block';
			throw Error(response.statusText);
		} else {
			var cookieName = 'token';
			var cookieValue = response.headers.get('authorization');
			var cookieExpDays = 10;
			setCookie(cookieName, cookieValue, cookieExpDays);
			var seconds = 3;
			document.getElementById('loginSuccess').style.display = 'block';
			document.getElementById('loginSuccess').innerText = `Success! Redirecting to index in ${seconds}`;
			document.getElementById('loginButton').style.display = 'none';
			document.getElementById('logoutButton').style.display = 'block';
			setInterval(function () {
				if (seconds === 0) {
					window.location = '/';
				}
				seconds -= 1;
				document.getElementById('loginSuccess').innerText = `Success! Redirecting to index in ${seconds}`;
			}, 1000);

		}

	}).catch(function (error) {
		console.error(error);
	});
}

function signupUser() {

	var username = document.getElementById('usernameSignup').value;
	var password = document.getElementById('passwordSignup').value;

	var endpoint = 'http://46.101.190.192:8080/user/signup';
	var method = 'POST';
	var body = JSON.stringify({
		username: username,
		password: password
	});

	var httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState === 4 && httpRequest.status === 200) {
			document.getElementById('signupSuccess').style.display = 'block';
		}
		if (httpRequest.status > 304) {
			document.getElementById('signupError').style.display = 'block';
		}
	};

	httpRequest.open(method, endpoint, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');
	httpRequest.send(body);
}

function isAuthorized () {
	// Get token cookie
	var cookie = getCookie('token');
	// "cookie" is not equal to false
	return cookie !== false;
}

function logoutUser () {
	deleteCookie('token');
	deleteCookie('username');
}
function loadAllComments() {
	var endpoint = 'http://46.101.190.192:8080/api/post/allComments';
	var method = 'get';
	var httpRequest = new XMLHttpRequest();

	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState === 4 && httpRequest.status === 200) {
			var allComments = JSON.parse(httpRequest.responseText);
			console.log('All comments: ', allComments);
			generateCommentsTable(allComments);
		}
	};

	httpRequest.open(method, endpoint, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send();
}

function generateCommentsTable(data) {
	console.log('data: ', data);
	for (var i = 0; i < data.length; i++) {

		// Variables for current data object
		var current = data[i];
		var href = current['post_url'];
		var postTitle = current['PostTitle'];
		var postText = current['PostText'];
		var id = current.id;
		var userName = current.userName;

		// Table body
		var tBody = document.getElementById('allCommentsBody');

		// classes tr
		var trTopClass = 'table__row--top';
		var trBotClass = 'table__row--bot';

		// Classes td top
		var tdTopUpvoteClass = 'td__top__upvote';
		var tdTopInfoClass = 'td__top__info';

		// Classes td bottom
		var tdBotEmptyClass = 'td__bottom__empty';
		var tdBotCommentClass = 'td__bottom__class';

		// Top section of the table - info and links
		var trTop = document.createElement('tr');
		trTop.classList.add(trTopClass);

		var tdTopUpvote = document.createElement('td');
		var tdTopUpvoteLink = document.createElement('a');
		tdTopUpvoteLink.href = `/vote?id=${id}`;
		tdTopUpvoteLink.setAttribute('onclick', 'return makeVote(event, this, "up")');
		tdTopUpvoteLink.innerText = '▲';
		tdTopUpvote.appendChild(tdTopUpvoteLink);
		tdTopUpvote.classList.add(tdTopUpvoteClass);

		var tdTopInfo = document.createElement('td');
		tdTopInfo.classList.add(tdTopInfoClass);
		tdTopInfo.setAttribute('colspan', '2');
		var infoString = `<a href="/user?username=${userName}">${userName}</a> | on: <a href="/item?id=${id}">${postTitle}</a>`;
		tdTopInfo.innerHTML = infoString;

		// Bottom section of the table - hidden and comment
		var trBottom = document.createElement('tr');
		trBottom.classList.add(trBotClass);

		var tdBotEmpty = document.createElement('td');
		tdBotEmpty.classList.add(tdBotEmptyClass);

		var tdBotComment = document.createElement('td');
		tdBotComment.classList.add(tdBotCommentClass);
		tdBotComment.innerText = postText;

		trTop.appendChild(tdTopUpvote);
		trTop.appendChild(tdTopInfo);

		trBottom.appendChild(tdBotEmpty);
		trBottom.appendChild(tdBotComment);

		tBody.appendChild(trTop);
		tBody.appendChild(trBottom);

		// Don't add spacer element after last element
		if (!(current === data[data.length -1])) {
			var trSpacer = document.createElement('tr');
			trSpacer.classList.add('table__row--spacer');
			trSpacer.setAttribute('colspan', '3');
			var tdSpacer = document.createElement('td');
			tdSpacer.setAttribute('colspan', 3);
			trSpacer.appendChild(tdSpacer);
			tBody.appendChild(trSpacer);
		}
	}
}
// Read about cookies here: https://www.w3schools.com/js/js_cookies.asp

function setCookie(cookieName, cookieValue, cookieExpDays) {
	var date = new Date();
	date.setTime(date.getTime() + (cookieExpDays * 24 * 60 * 60 * 1000));
	var expires = 'expires=' + date.toUTCString();
	document.cookie = cookieName + '=' + cookieValue + ';' + expires + ';path=/';
}

function getCookie(cookieName) {
	var name = cookieName + '=';
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');

	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) === ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) === 0) {
			return c.substring(name.length, c.length);
		}
	}
	return false;
}

function deleteCookie(cookiename) {
	document.cookie = cookiename + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// function checkCookie() {
// 	var username = getCookie('username');
// 	if (username !== '') {
// 		alert('Welcome back', username);
// 	} else {
// 		window.location.href('/login');
// 	}
// }
function loadAllPosts() {
	var endpoint = 'http://46.101.190.192:8080/api/post?page=1';
	var method = 'get';
	var httpRequest = new XMLHttpRequest();


	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState === 4 && httpRequest.status === 200) {
			var allPosts = JSON.parse(httpRequest.responseText);
			generatePostsTable(allPosts);
		}
	};

	httpRequest.open(method, endpoint, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send();
}

function generatePostsTable(data) {
	for (var i = 0; i < data.length; i++) {

		// Variables for current data object
		var current = data[i];
		var href = current['post_url'];
		var postTitle = current['post_title'];
		var id = current.id;
		var userName = current.username;

		// Table body
		var tBody = document.getElementById('allPostsBody');

		// Classes tr
		var trTopClass = 'table__row--top';
		var trBotClass = 'table__row--bot';

		// Classes td top
		var tdTopNumberClass = 'td__top__number';
		var tdTopUpvoteClass = 'td__top__upvote';
		var tdTopTitleClass = 'td__top__title';

		// Classes td bottom
		var tdBotEmptyClass = 'td__bottom__empty';
		var tdBotInfoClass = 'td__bottom__info';

		// Top section of table
		var trTop = document.createElement('tr');
		trTop.classList.add(trTopClass);
		var tdNumber = document.createElement('td');
		tdNumber.classList.add(tdTopNumberClass);
		var number = document.createElement('span');
		number.classList.add(`${tdTopNumberClass}--span`);
		number.innerText = i + 1;
		tdNumber.appendChild(number);
		var tdUpvote = document.createElement('td');
		tdUpvote.classList.add(tdTopUpvoteClass);
		var upvoteLink = document.createElement('a');
		upvoteLink.innerText = '▲';
		upvoteLink.href = `/vote?id=${id}`;
		upvoteLink.setAttribute('onclick', 'return makeVote(event, this, "up")');
		upvoteLink.classList.add(`${tdTopUpvoteClass}--link`);
		upvoteLink.classList.add('upvoteLink');

		tdUpvote.appendChild(upvoteLink);
		var tdTitle = document.createElement('td');
		tdTitle.classList.add(tdTopTitleClass);
		var titleLink = document.createElement('a');
		if (href) {
			titleLink.innerText = `${postTitle} (${href})`;
			titleLink.href = href;
		} else {
			titleLink.innerText = `${postTitle}`;
			titleLink.href = `/item?id=${id}`;
		}
		titleLink.classList.add(`${tdTopTitleClass}--link`);
		tdTitle.appendChild(titleLink);

		trTop.appendChild(tdNumber);
		trTop.appendChild(tdUpvote);
		trTop.appendChild(tdTitle);

		// Bottom section of table
		var trBottom = document.createElement('tr');
		trBottom.classList.add(trBotClass);
		var tdEmpty = document.createElement('td');
		tdEmpty.classList.add(tdBotEmptyClass);
		var tdInfo = document.createElement('td');
		tdInfo.classList.add(tdBotInfoClass);
		tdInfo.setAttribute('colspan', '2');
		var infoString = `<span class='score'>50 points</span> by <a href="/user?username=${userName}">${userName}</a> | <a href=/item?id=${id}>number of comments</a>`;
		tdInfo.innerHTML = infoString;
		trBottom.appendChild(tdEmpty);
		trBottom.appendChild(tdInfo);

		tBody.appendChild(trTop);
		tBody.appendChild(trBottom);

		// Don't add spacer element after last element
		if (!(current === data[data.length - 1])) {
			// Spacing section (separate posts)
			var trSpacer = document.createElement('tr');
			trSpacer.classList.add('table__row--spacer');
			trSpacer.setAttribute('colspan', '3');
			var tdSpacer = document.createElement('td');
			tdSpacer.setAttribute('colspan', 3);
			trSpacer.appendChild(tdSpacer);
			tBody.appendChild(trSpacer);
		}

	}
}
function loadItemData() {
	getItem();
}

function getItem() {
	let itemId = getItemIdFromQueryString('id');

	var endpoint = `http://46.101.190.192:8080/api/post/comments/${itemId}`;
	var method = 'GET';
	var httpRequest = new XMLHttpRequest();
	console.log('Item id: ', itemId);

	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState === 4 && httpRequest.status === 200) {
			// var allPosts = JSON.parse(httpRequest.responseText);
			console.log(httpRequest);
		}
	};

	httpRequest.open(method, endpoint, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send();
}

function addComment() {
	console.log('Add comment to item!');

	var itemComment = document.getElementById('itemComment').value;
	if (itemComment) {
		console.log('Item comment: ', itemComment);
	}
}

function generateCommentTable(data) {

}

comments = [
	{
		comment: 'comment one', comments: [
			{
				comment: 'comment two in comment two', comments: [
					{}
				]
			}
		]
	},
	{
		comment: 'comment two', comments: [
			{
				comment: 'comment two in comment two', comments: [
					{}
				]
			}
		]
	},
	{
		comment: 'comment three', comments: [
			{
				comment: 'comment three in comment three', comments: [
					{}
				]
			}
		]
	}
];
/* globals loadAllPosts */
var publicPage;
(function () {

	window.onload = function () {
		console.log('Window ready - main .js');
		var currentPath = window.location.pathname;
		var productionUrl = 'hackerNews-clone-project-frontend';
		var productionRegex = new RegExp(productionUrl, '');
		var isProduction = productionRegex.test(currentPath);

		tokenAndUsernameSet();

		var publicPages = [
			'/login/',
			'/comments/',
			'/'
		];

		if (isProduction) {
			for (var i = 0; i < publicPages.length; i++) {
				publicPages[i] = '/' + productionUrl + publicPages[i];
			}
		}

		publicPage = publicPages.indexOf(currentPath) !== -1;

		if (!isAuthorized() && !publicPage) {
			var divUnauthorized = document.getElementById('divUnauthorized');
			divUnauthorized.style.display = 'block';
			return;
		}
		var divAuthorized = document.getElementById('divAuthorized');
		divAuthorized.style.display = 'block';

		var indexRegex = isProduction ? new RegExp(`/${productionUrl}`, '') : /^\/$/;
		if (indexRegex.test(currentPath)) {
			// index.js function
			console.log('Index');
			loadAllPosts();
		}

		var commentsRegex = /^\/comments\/$/;
		if (commentsRegex.test(currentPath)) {
			loadAllComments();
		}

		var userPageRegex = /^\/user\/$/;
		if (userPageRegex.test(currentPath)) {
			loadUserProfile();
		}

		var itemPageRegex = /^\/item\/$/;
		if (itemPageRegex.test(currentPath)) {
			loadItemData();
		}

		// router
		var Router = {
			routes: {
				'/': function () {
					Router.makeCurrent('index');
				},
				'/new': function () {
					Router.makeCurrent('new');
				},
				'/comments': function () {
					Router.makeCurrent('comments');
				},
				'/show': function () {
					Router.makeCurrent('show');
				},
				'/ask': function () {
					Router.makeCurrent('ask');
				},
				'/jobs': function () {
					Router.makeCurrent('jobs');
				},
				'/submit': function () {
					Router.makeCurrent('submit');
				}
			},
			nav: function () {
				var route = window.location.pathname;
				if (route !== '/') {
					route = route.replace(/\/$/, '');
				}
				// Do not do it on login page
				if (!/login/.test(route) && !/item/.test(route) && !/user/.test(route) && !indexRegex.test(route)) {
					this.routes[route].apply();
				}
			},
			makeCurrent: function (item) {
				document.getElementById(`top-nav__link-${item}`).classList.add('active');

			}
		};

		Router.nav();
		document.getElementsByClassName('top-nav__link').onclick = Router.nav();
	};
})();

function tokenAndUsernameSet() {
	var tokenCookie = getCookie('token');
	var usernameCookie = getCookie('username');

	if (tokenCookie && usernameCookie) {
		document.getElementById('loginButton').style.display = 'none';
		document.getElementById('logoutButton').style.display = 'block';
	}
	// If logged in but username cookie not set
	if (tokenCookie && !usernameCookie) {
		document.getElementById('loginButton').style.display = 'none';
		document.getElementById('logoutButton').style.display = 'block';
		var token = getCookie('token');
		var tokenFilteredBearer = token.split(' ').pop();
		var encodedUsername = tokenFilteredBearer.split('.')[1];
		var decodedUsername = atob(encodedUsername);
		var cookieValue = JSON.parse(decodedUsername).username;
		var cookieName = 'username';
		var cookieExpDays = 10;
		setCookie(cookieName, cookieValue, cookieExpDays);
	} else if (!tokenCookie && usernameCookie) {
		deleteCookie('username');
	}
	return;
}
function getItemIdFromQueryString(field, selector) {
	var href = selector ? selector: window.location.href;
	var reg = new RegExp('[?&]' + field + '=([^&#]*)', 'i');
	var string = reg.exec(href);
	return string ? string[1] : null;
}
function submitPost() {
	// post_parent always set to -1 as there can't be any comment to a new post
	var postParent = "";
	// Same as above
	var postType = 'story';
	var title = document.getElementById('titleSubmit').value;
	var url = document.getElementById('urlSubmit').value;
	console.log(url)
	var text = document.getElementById('textSubmit').value;
	var token = getCookie('token');
	var username = getCookie('username');

	if (!title) {
		document.getElementById('submitError').style.display = 'block';
		return;
	}

	if (title && url && text) {
		document.getElementById('submitError').style.display = 'block';
		return;
	}

	var endpoint = 'http://46.101.190.192:8080/api/post';
	var method = 'post';
	var body = JSON.stringify({
		post_title: title,
		post_text: text,
		post_url: url,
		post_type: postType,
		post_parent: postParent,
		username: username
	});

	var request = new Request(endpoint, {
		method: method,
		body: body,
		mode: 'CORS',
		headers: new Headers({
			'Authorization': token,
			'Content-Type': 'application/json'
		})
	});

	fetch(request).then(function (response) {
		if (!response.ok) {
			console.log(response)
			document.getElementById('submitError').innerHTML = '<span class="closebtn" onclick="this.parentElement.style.display=\'none\';">×</span>Something went wrong... Please try again';
			document.getElementById('submitError').style.display = 'block';
			throw Error(response.statusText);
		}
		window.location = '/';
	});
}
function loadUserProfile() {
	getUserProfile();
}

function getUserProfile() {
	var username = getItemIdFromQueryString('username');

	var endpoint = `http://46.101.190.192:8080/user?username=${username}`;
	var method = 'GET';
	var httpRequest = new XMLHttpRequest();

	httpRequest.onreadystatechange = function () {
		if (httpRequest.readyState === 4 && httpRequest.status === 200) {
			var userProfile = JSON.parse(httpRequest.responseText);
			var created = userProfile.createdAt;
			var karma = userProfile.karma;

			console.log('Created: ', created);

			var usernameElement = document.getElementById('userProfile');
			var createdElement = document.getElementById('createdText');
			var karmaElement = document.getElementById('karmaText');

			usernameElement.innerText = username;
			createdElement.innerText = created;
			karmaElement.innerText = karma;
		}
	};

	httpRequest.open(method, endpoint, true);
	httpRequest.setRequestHeader('Content-Type', 'application/json');
	httpRequest.send();
}
function makeVote(event, ele, how) {
	// Stop link from navigating
	event.preventDefault();

	var elementHref = ele.href;
	var postId = getItemIdFromQueryString('id', elementHref);
	var username = getCookie('username');
	var token = getCookie('token');

	upvoteHttp(postId, username, token);
}

function upvoteHttp(postId, username, token) {
	var endpoint = 'http://46.101.190.192:8080/api/post/vote';
	var method = 'POST';
	var body = JSON.stringify({
		post_id: postId,
		username: username,
		mode: 'up'
	});

	var request = new Request(endpoint, {
		method: method,
		headers: new Headers({
			'Access-Control-Allow-Origin': 'http://46.101.190.192:8080',
			'Authorization': token,
			'Content-Type': 'application/json'
		}),
		body: body
	});

	fetch(request).then(function (response) {
		if (!response.ok) {
			alert('Something went wrong');
			throw Error(response.statusText);
		} else {
			console.log(response);
		}
	});
}


