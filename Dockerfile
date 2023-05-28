# build environment
FROM openjdk:17-alpine
#create app volume
VOLUME /tmp
# install app dependencies
COPY target/*.jar app.jar
# assign app port
EXPOSE 8080
# run the web service on container startup
ENTRYPOINT ["java","-jar", "app.jar"]

