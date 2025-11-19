package app.adapter.in.client;

import app.adapter.in.builder.PolicyBuilder;
import app.application.usecases.PolicyUseCase;
import app.domain.model.Policy;
import app.domain.services.BillingService;
import app.domain.model.enums.TypePolicy;
import java.sql.Date;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PolicyClient {
    private static final String MENU = "Ingrese una opcion: \n" +

            "1. Crear ARL Sura \n" +
            "2. Crear Positiva Seguros \n" +
            "3. Crear EPS Sura \n" +
            "4. Crear EPS Sanitas. \n" +
            "5. crear Coomeva. \n" +
            "6. Regresar al menu anterior. \n";

    private static Scanner reader = new Scanner(System.in);

    @Autowired
    private PolicyUseCase policyUseCase;
    @Autowired
    private PolicyBuilder policyBuilder;
    @Autowired
    private BillingService billingService;

    public void session() {

        boolean session = true;
        while (session) {
            session = menu();
        }

    }

    private boolean menu() {

        try {

            System.out.println(MENU);
            String option = reader.nextLine();

            switch (option) {

                case "1": {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.createARLSura(policy);
                    return true;
                }

                case "2": {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.cratePositivaSeguros(policy);
                    return true;
                }

                case "3": {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.crateEPSSura(policy);
                    return true;
                }

                case "4": {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.crateEPSSanitas(policy);
                    return true;
                }

                case "5": {
                    Policy policy = readInfoFromPolicy();
                    policyUseCase.crateEPSSanitas(policy);
                    return true;
                }

                case "6": {
                    System.out.println("Salindo de seguros...");
                    return false;
                }

                case "7": {
                    System.out.println("Generando factura...");
                    // Ejemplo de datos para facturación
                    Policy policy = new Policy(); // Aquí se debe obtener la póliza real del paciente
                    policy.setPolicyName(TypePolicy.ARL_SURA);
                    policy.setPolicyNumber(12345L);
                    policy.setPolicyStatus(true);
                    policy.setPolicyTerminationDate(Date.valueOf("2025-12-31"));

                    billingService.generateInvoice(
                            "Juan Pérez", 30, "1234567890", "Dr. Gómez", policy, 200000, 50000);
                    return true;
                }

                default: {
                    System.out.println("Ingrese una opcion valida.");
                    return true;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }

    }

    private Policy readInfoFromPolicy() throws Exception {

        System.out.println("Ingrese documento de admin: ");
        String documentA = reader.nextLine();
        System.out.println("Ingrese documento de paciente: ");
        String documentP = reader.nextLine();
        System.out.println("Numero de poliza del paciente: ");
        String policyNumber = reader.nextLine();
        System.out.println("Estado de poliza: 1 - activa | 2 - inactiva. :");
        String policyStatus = reader.nextLine();

        // Solicitar fecha de inicio de la póliza
        java.sql.Date policyStartDate = null;
        while (policyStartDate == null) {
            System.out.println("Ingrese la fecha de inicio de la póliza (yyyy-MM-dd): ");
            String input = reader.nextLine();
            try {
                policyStartDate = java.sql.Date.valueOf(input);
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Intente de nuevo.");
            }
        }

        return policyBuilder.policyBuilder(documentA, documentP, policyNumber, policyStatus, policyStartDate);

    }

}
