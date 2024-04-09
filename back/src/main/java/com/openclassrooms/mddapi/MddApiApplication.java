package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MddApiApplication {

	//TODO : 
	// - uniformiser nom des méthodes, nom des classes (singulier / pluriel, ...)
	// - uniformiser logs 
	// - vérifier que dans les Dto, les sous objets sont des Dto 


	public static void main(String[] args) {
		SpringApplication.run(MddApiApplication.class, args);
	}

}
