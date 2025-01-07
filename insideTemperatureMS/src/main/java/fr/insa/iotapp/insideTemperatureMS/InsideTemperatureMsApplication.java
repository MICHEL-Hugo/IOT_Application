package fr.insa.iotapp.insideTemperatureMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InsideTemperatureMsApplication {
	
    @Bean
    @LoadBalanced // Permet de résoudre les noms des services enregistrés sur Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(InsideTemperatureMsApplication.class, args);
	}

}
