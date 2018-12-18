package com.drz.place;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.drz.place.*",
})
@SpringBootApplication
public class PlaceApplication {



	public static void main(String[] args) {

		SpringApplication.run(PlaceApplication.class, args);

	}

}

