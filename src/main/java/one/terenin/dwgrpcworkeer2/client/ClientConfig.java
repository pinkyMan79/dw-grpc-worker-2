package one.terenin.dwgrpcworkeer2.client;

import one.terenin.ConsumerGrpcApiGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
public class ClientConfig {

    @Bean
    public ConsumerGrpcApiGrpc.ConsumerGrpcApiBlockingStub consumerGrpcApiBlockingStub(GrpcChannelFactory factory) {
        return ConsumerGrpcApiGrpc.newBlockingStub(factory.createChannel("127.0.0.1:9978"));
    }

    @Bean
    public ConsumerGrpcApiGrpc.ConsumerGrpcApiStub consumerGrpcApiStub(GrpcChannelFactory factory) {
        return ConsumerGrpcApiGrpc.newStub(factory.createChannel("127.0.0.1:9978"));
    }

}
