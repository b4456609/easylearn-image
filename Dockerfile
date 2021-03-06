FROM java:8-jre
COPY dev.yml /opt/dropwizard/
COPY build/libs/image-1.0-SNAPSHOT-all.jar /opt/dropwizard/
EXPOSE 8080
EXPOSE 8081
WORKDIR /opt/dropwizard
CMD ["java", "-jar", "image-1.0-SNAPSHOT-all.jar", "server", "dev.yml"]