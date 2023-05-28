FROM azul/zulu-openjdk-alpine:17
ENV PORT 9090
EXPOSE 9090
#COPY target/*.jar /canalbox-component
WORKDIR /opt
CMD ["java", "-jar", "canalbox-component.jar"]

