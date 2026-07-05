# 베이스 이미지: Java 17 JDK가 설치된 리눅스 환경
FROM eclipse-temurin:17-jdk-jammy
# 컨테이너 내부 작업 디렉토리 생성 및 이동
WORKDIR /app
# 현재 폴더(프로젝트 전체)를 컨테이너 안으로 복사
COPY . .
# gradlew 실행 권한 부여 (권한 없으면 빌드 시 에러 발생)
RUN chmod +x gradlew
# Gradle로 프로젝트 빌드 → jar 파일 생성 → app.jar 파일로 카피
RUN ./gradlew bootJar
RUN cp build/libs/*.jar app.jar
# 컨테이너가 사용할 포트 명시 (문서화 목적)
EXPOSE 8080
# 컨테이너 실행 시 수행할 명령어: jar 파일 실행
ENTRYPOINT ["java", "-jar", "app.jar"]