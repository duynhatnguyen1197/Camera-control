Build file war with maven

```
export M2_HOME=/Users/nguyenduy/apache-maven-3.6.3
export M2=$M2_Home/bin
export PATH=$PATH:$M2_HOME/bin

mvn package
```

Copy .war to tomcat server
```
cp target/complete.war /Users/nguyenduy/Desktop/MyApp/apache-tomcat-9.0.34/webapps

cd /Users/nguyenduy/Desktop/MyApp/apache-tomcat-9.0.34
./bin/catalina.sh stop 
./bin/catalina.sh start
```