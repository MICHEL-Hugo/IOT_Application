package fr.insa.iotapp.outsideTemperatureMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class OutsideTemperatureMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutsideTemperatureMsApplication.class, args);
	}

}
