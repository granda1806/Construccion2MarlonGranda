package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AplicacionApplication.class, args);
	}

        @Override
        public void run(String... args) throws Exception {
            System.out.println("Esta corriendo...");
        }
}
