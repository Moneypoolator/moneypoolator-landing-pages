#! /bin/sh

service tomcat6 stop
service mysqld stop
rm -rvf /usr/share/tomcat6/webapps/*
history
cp -v landing-page.war /usr/share/tomcat6/webapps/
service mysqld start
service tomcat6 start
mysqldump -u root -p landing_page >dump_landing_page_2013-05-13.sql