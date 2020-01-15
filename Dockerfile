FROM oracle/graalvm-ce:19.3.0-java8 as graalvm
#FROM oracle/graalvm-ce:19.3.0-java11 as graalvm # For JDK 11
COPY . /home/app/coffee-shop
WORKDIR /home/app/coffee-shop
RUN gu install native-image
RUN native-image --no-server --static -cp target/coffee-shop-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/coffee-shop/coffee-shop /app/coffee-shop
ENTRYPOINT ["/app/coffee-shop", "-Djava.library.path=/app"]
