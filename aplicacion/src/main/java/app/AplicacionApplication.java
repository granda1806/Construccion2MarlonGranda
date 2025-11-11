/**/
package app;

import app.adapter.in.client.LoginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app")
public class AplicacionApplication implements CommandLineRunner {

    @Autowired
    private LoginClient login;

    public static void main(String[] args) {
        SpringApplication.run(AplicacionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Aplicación corriendo correctamente...");
        login.session();
    }
}
