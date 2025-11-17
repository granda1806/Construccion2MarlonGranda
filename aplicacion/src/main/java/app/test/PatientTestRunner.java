package app.test;

import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@Order(0)
public class PatientTestRunner implements CommandLineRunner {

    private final PatientRepository patientRepository;

    public PatientTestRunner(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo ejecutar si la propiedad del sistema test.run está activada
        if (!Boolean.getBoolean("test.run")) {
            return;
        }

        System.out.println("Iniciando prueba: crear paciente de test y buscarlo...");

        Long document = 999999999L;

        // Eliminar cualquier registro previo con ese documento
        Optional<PatientEntity> existing = patientRepository.findByDocument(document);
        existing.ifPresent(e -> {
            patientRepository.deleteById(e.getId());
            System.out.println("Registro previo eliminado (id=" + e.getId() + ")");
        });

        PatientEntity p = new PatientEntity();
        p.setName("Paciente Test");
        p.setDocument(document);
        p.setGender("M");
        p.setAddress("Calle Falsa 123");
        p.setPhoneNumber("3001234567");
        p.setEmail("test@example.com");
        p.setEmergencyContactName("Contacto Test");
        p.setEmergencyContactNumber("3007654321");
        p.setRelationshipPatient("Amigo");

        patientRepository.save(p);
        System.out.println("Paciente de prueba creado con documento=" + document);

        Optional<PatientEntity> fetched = patientRepository.findByDocument(document);
        if (fetched.isPresent()) {
            PatientEntity found = fetched.get();
            System.out.println("Búsqueda OK: encontrado paciente id=" + found.getId() + ", nombre=" + found.getName());
        } else {
            System.out.println("Búsqueda FALLIDA: paciente no encontrado después de crear.");
        }

        // Salimos para no arrancar la interfaz interactiva posteriormente
        System.out.println("Prueba finalizada. Saliendo.");
        System.exit(0);
    }
}
