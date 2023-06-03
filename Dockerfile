FROM openjdk:20
WORKDIR hudeem-docker
COPY . .
ADD build/libs/hudeem-0.0.1-SNAPSHOT.jar hudeem.jar
ENTRYPOINT ["java", "-jar", "hudeem.jar"]