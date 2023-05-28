#FROM azul/zulu-openjdk-alpine:17
#ENV PORT 9090
#EXPOSE 9090
#COPY target/*.jar canalbox-component
#
#WORKDIR /opt
#CMD ["java", "-jar", "canalbox-component.jar"]

# build environment
FROM openjdk:11
#create app volume
VOLUME /tmp

# install app dependencies
COPY target/*.jar canalbox-component.jar

# assign app port
EXPOSE 8080
# run the web service on container startup
ENTRYPOINT ["java","-jar", "canalbox-component.jar"]



