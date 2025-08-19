FROM eclipse-temurin:17-jdk AS build
WORKDIR /workspace/app


COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN ./gradlew build
# /workspace/app/build/libs/*-SNAPSHOT.jar

FROM eclipse-temurin:17-jdk

COPY --from=build /workspace/app/build/libs/*-SNAPSHOT.jar app.jar
COPY script/entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

# java -jar app.jar
# ENTRYPOINT java -jar app.jar - 사용하지 않는게 정신건강에 이롭다
# ENTRYPOINT ["java","-jar","app.jar"]
ENTRYPOINT ["/entrypoint.sh"]