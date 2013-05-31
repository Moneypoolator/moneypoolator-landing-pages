#! /bin/sh

service --status-all
iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
iptables -t nat -I PREROUTING -p tcp --dport 443 -j REDIRECT --to-port 8443
iptables -A INPUT -p tcp -m tcp --dport 8080 -j ACCEPT
iptables -A INPUT -p tcp -m tcp --dport 80 -j ACCEPT
iptables -A INPUT -p tcp -m tcp --dport 8443 -j ACCEPT
iptables -A INPUT -p tcp -m tcp --dport 443 -j ACCEPT
iptables-save > /etc/sysconfig/iptables

# yum install emacs
# emacs /etc/sysconfig/iptables

service --status-all
iptables -A INPUT -i lo -j ACCEPT
iptables -A OUTPUT -o lo -j ACCEPT
iptables-save > /etc/sysconfig/iptables

chkconfig iptables on
service iptables start


yum install mysql-server
chkconfig --add mysqld
chkconfig mysqld on
service mysqld start
/usr/bin/mysql_secure_installation

yum install tomcat6
chkconfig --add tomcat6
chkconfig tomcat6 on
service tomcat6 start



