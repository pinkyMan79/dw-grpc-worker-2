package one.terenin.dwgrpcworkeer2.common.config;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    /*@Bean
    public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> factoryCustomizer() {
        return customiser -> {
            customiser.setHttp2();
        }
    }*/

}
