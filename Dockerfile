FROM oracle/graalvm-ce:1.0.0-rc13 as graalvm
COPY . /home/app/kafka-to-mongo
WORKDIR /home/app/kafka-to-mongo
RUN native-image --no-server -cp target/kafka-to-mongo-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/kafka-to-mongo .
ENTRYPOINT ["./kafka-to-mongo"]
