package app;

import app.adapter.in.client.AdminClient;
import app.adapter.in.client.HResourcesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacionApplication implements CommandLineRunner{
    
        @Autowired
        private AdminClient clientAdmin;
        
	public static void main(String[] args)
        {
		SpringApplication.run(AplicacionApplication.class, args);
	}

        @Override
        public void run(String... args) throws Exception
        {
            System.out.println("Esta corriendo...");
            clientAdmin.session();
        }
        
}
