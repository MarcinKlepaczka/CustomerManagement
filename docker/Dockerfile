# Dockerfile

FROM openjdk:8-jre
WORKDIR /
#add required jars
COPY customer-management-0.0.1-SNAPSHOT.jar /customer-management-0.0.1-SNAPSHOT.jar

#expose port
EXPOSE 50000
#cmd to execute
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/customer-management-0.0.1-SNAPSHOT.jar"]