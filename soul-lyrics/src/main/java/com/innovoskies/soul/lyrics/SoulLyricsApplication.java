package com.innovoskies.soul.lyrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.innovoskies.soul.lyrics")
public class SoulLyricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoulLyricsApplication.class, args);
	}

}
