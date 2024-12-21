package fr.insa.iotapp.decisionMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling 
@SpringBootApplication
public class DecisionMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecisionMsApplication.class, args);
	}
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
