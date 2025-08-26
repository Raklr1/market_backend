FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /workspace/app

# 复制构建文件（单独复制 pom.xml 和源码以最大化利用缓存）
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 下载依赖（如果 pom.xml没变，这一层会被缓存，极大加速构建）
RUN ./mvnw dependency:go-offline -B

# 复制源代码并打包
COPY src src
RUN ./mvnw package -DskipTests

# 解压打包好的 jar，为下一阶段做准备
RUN mkdir -p target/dependency & (cd target/dependency; jar -xf ../*.jar)

# 第二阶段，最小化运行时镜像
FROM eclipse-temurin:21-jre-jammy AS runtime

# 创建一个非 root 用户运行应用（安全最佳实践）
RUN groupadd -r spring && useradd -r -g spring spring
USER spring

# 暴露端口（与 application.yaml 中配置的端口一致）
EXPOSE 8080

# 从 builder 阶段复制依赖项
COPY --from=builder --chown=spring:spring /workspace/app/target/dependency/BOOT-INF/lib /app/lib
COPY --from=builder --chown=spring:spring /workspace/app/target/dependency/META-INF /app/META-INF
COPY --from=builder --chown=spring:spring /workspace/app/target/dependency/BOOT-INF/classes /app

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -cp app:app/lib/* ${MAIN_CLASS:-top.otsuland.market.MarketApplication}"]