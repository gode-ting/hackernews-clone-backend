# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://atlas.hashicorp.com/search.
  config.vm.box = "ubuntu/xenial64"

  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  #config.vm.network "forwarded_port", guest: 80, host: 8080
  #config.vm.network "forwarded_port", guest: 8080, host: 8080
  # mongodb port
  #config.vm.network "forwarded_port", guest: 27017, host: 27017

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  # config.vm.network "private_network", ip: "192.168.33.10"
  config.vm.network "forwarded_port", guest: 8080, host: 8080

  
  
  # for mongoDB server
  config.vm.network "forwarded_port", guest: 27017, host: 27017
  # config.vm.network "forwarded_port", guest: 27018, host: 27018
  # config.vm.network "forwarded_port", guest: 27019, host: 27019
  config.vm.network "forwarded_port", guest: 28017, host: 28017
  # for MySQL DB server
  config.vm.network "forwarded_port", guest: 3306, host: 3306

 
  # config.vm.network "private_network", type: "dhcp"

  # config.vm.network "public_network"


  config.vm.provider "virtualbox" do |vb|
    # Display the VirtualBox GUI when booting the machine
    # vb.gui = true

    # Customize the amount of memory on the VM:

    vb.name = "dbprod"
  end


  config.vm.provision "shell", privileged: false, inline: <<-SHELL

    echo "Installing MongoDB"
    sudo apt-get -y install mongodb-server
   
    sudo mkdir -p /data/db
    # TODO:
    # /etc/mongodb.conf modify bind_ip line to 0.0.0.0 and restart server
    sudo sed -i '/bind_ip = / s/127.0.0.1/0.0.0.0/' /etc/mongodb.conf
    # sudo systemctl restart mongod
    sudo service mongodb restart
    # sudo mongod
    sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password pwd'
    sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password pwd'
    sudo apt-get update
    sudo apt-get install -y mysql-server mysql-client
    sudo chmod a+w /etc/mysql/mysql.cnf
    sudo echo "skip-external-locking" >> /etc/mysql/mysql.cnf
    sudo echo "bind-address=0.0.0.0" >> /etc/mysql/mysql.cnf
    sudo /etc/init.d/mysql restart
    mysql -u root -ppwd -e "create user 'root'@'10.0.2.2' identified by 'pwd'; grant all privileges on *.* to 'root'@'10.0.2.2' with grant option; flush privileges;"
    sudo /etc/init.d/mysql restart
    # sudo service mysql restart
    # sudo /etc/init.d/mysql restart
  SHELL
end
