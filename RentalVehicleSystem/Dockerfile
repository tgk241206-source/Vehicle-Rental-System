# 1. Thay doi image cu bang image moi (eclipse-temurin)
FROM eclipse-temurin:17-jdk-jammy

# 2. Thiet lap thu muc lam viec
WORKDIR /app

# 3. Copy source code
COPY src/ ./src/

# 4. Tao thu muc bin
RUN mkdir bin

# 5. Bien dich code
RUN javac -d bin $(find src -name "*.java")

# 6. Chay chuong trinh
CMD ["java", "-cp", "bin", "Main"]