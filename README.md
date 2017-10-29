## Backend Repository for our project in Large Sytems on Cphbusiness, PBA in software development (fall 2017).

## Github links of authors

[Frederik Larsen](https://github.com/lalelarsen)

[Anders Bjergfelt](https://github.com/andersbjergfelt)

[Emil Gräs](https://github.com/emilgras)

[Daniel Hillmann](https://github.com/hilleer)

## Notes

[Hacker news](https://news.ycombinator.com/)

In this project we are using Spring Framework

[Spring Framework](https://spring.io/)

The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application,
but there are extensions for building web applications on top of the Java EE (Enterprise Edition) platform.

## Installetion
 
1. ```sh 
   $ git clone https://github.com/gode-ting/hackernews-clone-backend.git
   ```

2. ```sh
  $ cd hackernews-clone-backend
  ```  

## Running the project

1. ```sh
  vagrant up
  ```  
2. Run the main project in NetBenas

## Accessing Data with MongoDB

Define a simple model:

```java
public class Article {  
    @Id
    public String id;
    
    public String link;
    
    public String headline; 
    
    public Article(){}
    
    public Article(String link, String headline){
        this.link = link;
        this.headline = headline;
    }
    @Override
    public String toString() {
        return "Article{" + "link=" + link + ", headline=" + headline + '}';
    }  
}

```
The typical getters and setters have been left out for brevity.

Id fits the standard name for a MongoDB id so it doesn’t require any special annotation to tag it for Spring Data MongoDB.

Spring MongoDB will map the class Article into a collection called article. If you want to change the name of the collection, you can use Spring Data MongoDB’s @Document annotation on the class.

## Create simple queries

You can simply write a handful of methods and the queries are written for you.

Create a repository interface that queries Article documents.

```java
@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    public Article findByHeadline(String firstName);
}
```
ArticleRepository extends the MongoRepository interface and plugs in the type of values and id it works with: Artrile and String. Out-of-the-box, this interface comes with many operations, including standard CRUD operations (create-read-update-delete).

You can define other queries as needed by simply declaring their method signature.

In a typical Java application, you write a class that implements AticleRepository and craft the queries yourself. What makes Spring Data MongoDB so useful is the fact that you don’t have to create this implementation. Spring Data MongoDB creates it on the fly when you run the application.

## Application class / main class

To include MongoRepo = 
```java
@EnableMongoRepositories ("com.daef.repositories")
```

```java
@SpringBootApplication(scanBasePackages = { "com.daef.repositories" })
```
@SpringBootApplication is a convenience annotation that adds all of the following:

@Configuration tags the class as a source of bean definitions for the application context.

@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.

Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.

@ComponentScan tells Spring to look for other components, configurations, and services in the daef package, allowing it to find the controllers.

The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.

Application includes a main() method that autowires an instance of ArticleRepository: Spring Data MongoDB dynamically creates a proxy and injects it there. 

Spring Boot by default attempts to connect to a locally hosted instance of MongoDB.

In application.properties at hackerNews-clone-backend\src\main\resources\application.properties you can change uri.

List of available properties: 
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html


TODO: Implement SQL DB

## Implementing JWT authentication and authorization on Spring Boot API's

In this section we will explain how we can esnure our endpoints in the most secure way using authentication tokens. Everything you will see here is inspired by this article: https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/



