package co.analisys.gimnasio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.ComponentScan;

@EnableRabbit
@SpringBootApplication
@EnableFeignClients
public class EquiposApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquiposApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
