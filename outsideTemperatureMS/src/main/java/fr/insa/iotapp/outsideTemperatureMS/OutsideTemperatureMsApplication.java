package fr.insa.iotapp.outsideTemperatureMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableScheduling
public class OutsideTemperatureMsApplication {
	
		@Bean
	    @LoadBalanced // Permet de résoudre les noms des services enregistrés sur Eureka
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	public static void main(String[] args) {
		SpringApplication.run(OutsideTemperatureMsApplication.class, args);
	}

}
