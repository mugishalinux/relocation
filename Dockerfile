FROM azul/zulu-openjdk-alpine:17
ENV PORT 9090
EXPOSE 9090

COPY target/canalbox-component.jar /opt/canalbox-component/

WORKDIR /opt/canalbox-component

CMD ["java", "-jar", "canalbox-component.jar"]

