package xyz.mmkmou.bootcamp.transactions.config;

import jakarta.ws.rs.ApplicationPath;
import xyz.mmkmou.bootcamp.transactions.api.controllers.TransactionController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



@Component
@Configuration
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        register(TransactionController.class);
    }
}
