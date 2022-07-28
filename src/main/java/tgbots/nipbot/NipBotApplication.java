package tgbots.nipbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NipBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(NipBotApplication.class, args);
    }
}
