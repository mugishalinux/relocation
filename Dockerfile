FROM azul/zulu-openjdk-alpine:17
ENV PORT 9090
EXPOSE 9090

COPY ./target/canalbox-component.jar app.jar

CMD ["java", "-jar", "canalbox-component.jar"]

