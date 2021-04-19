package com.innovoskies.soul.lyrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.innovoskies.soul.lyrics.group.repositories")
@ComponentScan("com.innovoskies.soul.lyrics")
public class SoulLyricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoulLyricsApplication.class, args);
	}

}
