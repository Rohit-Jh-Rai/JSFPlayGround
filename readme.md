# JEE7 application to test JSF and Richfaces, CDI and other JEE7 related subjects

## Needed
1. JDK 8
2. Maven 3.6
3. JBoss EAP7 applicationserver

## Steps
1. Make sure the following pooled datasource is configured inside JBoss:
   java:jboss/datasources/ExampleDS

        <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
          <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
          <driver>h2</driver>
          <security>
            <user-name>sa</user-name>
            <password>sa</password>
          </security>
        </datasource>

2. mvn clean install
3. create a Run-configuration in IntelliJ for JBoss
- Choose JSFRichEx1:war exploded as build-artifacts
- After started: in your browser hit http://localhost:8080/JSFRichEx1