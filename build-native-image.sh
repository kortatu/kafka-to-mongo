#!/usr/bin/env bash
#export PATH=$HOME/software/graal/bin:$PATH
./mvnw package
#java -cp target/kafka-to-mongo-0.1.jar io.micronaut.graal.reflect.GraalClassLoadingAnalyzer
native-image --no-server -cp target/kafka-to-mongo-*.jar \
             --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
             --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom,com.sun.jndi.dns.DnsClient,com.mongodb.UnixServerAddress

#native-image --no-server \
#             --class-path target/kafka-to-mongo-0.1.jar \
#             -H:ReflectionConfigurationFiles=target/reflect.json \
#             -H:EnableURLProtocols=http \
#             -H:IncludeResources="logback.xml|application.yml|META-INF/services/*.*" \
#             -H:Name=kafka-to-mongo \
#             -H:Class=kafkatomongo.Application \
#             -H:+ReportUnsupportedElementsAtRuntime \
#             -H:+AllowVMInspection \
#             -H:-UseServiceLoaderFeature \
#             --allow-incomplete-classpath \
#             --rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
#             --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom,com.sun.jndi.dns.DnsClient
