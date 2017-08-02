Reference example:
http://www.mkyong.com/spring-mvc/gradle-spring-mvc-web-project-example/

http://websystique.com/springmvc/spring-mvc-4-angularjs-example/
http://jasonwatmore.com/post/2015/03/10/angularjs-user-registration-and-login-example-tutorial
http://ui-grid.info/docs/#/tutorial/205_row_editable

Commands:
To create eclipse project
./gradlew eclipse

The gradle.build file is defined an embedded Jetty container. Issues gradle jettyRun to start the project.
./gradlew jettyRun

#A WAR file will be created in project\build\libs folder.
./gradlew war
