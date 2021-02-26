package za.co.newplant.musicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MusicapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicapiApplication.class, args);
	}

}
