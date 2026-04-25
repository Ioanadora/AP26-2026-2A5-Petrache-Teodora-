package com.lab7.tema7.lab7t;
import com.lab7.tema7.lab7t.client.FilmClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab7tApplication {

	public static void main(String[] args) {
		var run = SpringApplication.run(Lab7tApplication.class, args);
		FilmClient client = run.getBean(FilmClient.class);
		client.runClient();
	}
}
