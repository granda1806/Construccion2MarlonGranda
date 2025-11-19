
package app.adapter.in.client;

import app.infrastructure.persistence.repository.DoctorRepository;
import app.infrastructure.persistence.entities.DoctorEntity;
import java.util.Optional;

import app.adapter.in.builder.AppointmentBuilder;
import app.adapter.in.builder.UserBuilder;
import app.application.usecases.AdminUseCase;
import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.model.Person;
import app.domain.model.Policy;
import app.domain.ports.UserPortPatient;
import app.domain.ports.DoctorPortOut;
import app.domain.ports.PolicyPort;
import app.application.usecases.CreateInvoiceUseCase;
import app.application.usecases.PrintInvoiceUseCase;
import app.domain.model.Invoice;
import app.domain.model.User;
import app.domain.ports.UserPortOut;
import app.domain.model.enums.Role;

// Imports de fecha removidos porque no se usan en este archivo
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminClient {

    private static final String MENU = "Ingrese una opcion: \n" +
            " 1. Crear paciente \n" +
            " 2. Programar cita \n" +
            " 3. Facturacion \n" +
            " 4. Seguros medicos \n" +
            " 5. Regresar al menu principal.";

    private static final Scanner reader = new Scanner(System.in);

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AdminUseCase adminUseCase;
    @Autowired
    private UserBuilder userBuilder;
    @Autowired
    private AppointmentBuilder appointmentBuilder;

    @Autowired
    private PolicyClient policyClient;

    @Autowired
    private UserPortPatient userPortPatient;
    @Autowired
    private DoctorPortOut doctorPortOut;
    @Autowired
    private PolicyPort policyPort;
    @Autowired
    private CreateInvoiceUseCase createInvoiceUseCase;
    @Autowired
    private PrintInvoiceUseCase printInvoiceUseCase;

    @Autowired
    private UserPortOut userPortOut;

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
                    Patient patient = readInfoFromPatient();
                    adminUseCase.createPatient(patient);
                    return true;
                }

                case "2": {
                    Appointment appointment = readInfoFromAppointment();
                    adminUseCase.createAppointment(appointment);

                    // --- Facturación inmediata tras programar la cita ---
                    try {
                        // Buscar paciente
                        Patient patient = appointment.getPatient();
                        if (patient == null || patient.getDocument() == null) {
                            System.out.println("No se pudo obtener el paciente para facturación.");
                            return true;
                        }
                        Patient patientQuery = new Patient();
                        patientQuery.setDocument(patient.getDocument());
                        patient = userPortPatient.findByDocument(patientQuery);
                        if (patient == null) {
                            System.out.println("Paciente no encontrado para facturación.");
                            return true;
                        }

                        // Solicitar documento del doctor
                        System.out.print("Ingrese cédula del doctor: ");
                        String docDoctorStr = reader.nextLine();
                        Long docDoctor = null;
                        try {
                            docDoctor = Long.parseLong(docDoctorStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Cédula inválida. Debe ser numérica.");
                            return true;
                        }

                        // Buscar doctor como usuario y validar rol
                        User doctor = null;
                        try {
                            User doctorQuery = new User();
                            doctorQuery.setDocument(docDoctor);
                            doctor = userPortOut.findByDocument(doctorQuery);
                            if (doctor == null || doctor.getRole() == null || !doctor.getRole().equals(Role.DOCTOR)) {
                                System.out.println("Doctor no encontrado o no tiene rol de DOCTOR.");
                                return true;
                            }
                        } catch (Exception e) {
                            System.out.println("Error buscando doctor: " + e.getMessage());
                            return true;
                        }

                        // Buscar doctor en la tabla doctors y usar su id real
                        Long doctorId = null;
                        String doctorName = null;
                        Optional<DoctorEntity> doctorEntityOpt = doctorRepository.findByDocument(docDoctor);
                        if (doctorEntityOpt.isPresent()) {
                            doctorId = doctorEntityOpt.get().getId();
                            doctorName = doctorEntityOpt.get().getName();
                        } else {
                            System.out.println("El doctor no está registrado en la tabla de médicos.");
                            return true;
                        }
                        // Asignar el id correcto al objeto doctor
                        doctor.setId(doctorId);
                        doctor.setNameComplete(doctorName);

                        // Buscar póliza activa del paciente
                        Policy policy = null;
                        try {
                            java.util.List<Policy> policies = policyPort.findByPatient(patient);
                            if (policies != null && !policies.isEmpty()) {
                                policy = policies.stream().filter(Policy::isPolicyStatus).findFirst().orElse(null);
                            }
                        } catch (Exception e) {
                            System.out.println("Error buscando póliza: " + e.getMessage());
                        }

                        // Solicitar monto total de servicios
                        System.out.print("Ingrese el valor total de los servicios: $");
                        String amountStr = reader.nextLine();
                        double amount = 0;
                        try {
                            amount = Double.parseDouble(amountStr);
                            if (amount <= 0) {
                                System.out.println("El monto debe ser mayor que 0.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Monto inválido. Debe ser numérico.");
                            return true;
                        }

                        // Crear factura
                        Invoice invoice = null;
                        try {
                            invoice = createInvoiceUseCase.execute(patient, doctor, policy, amount);
                        } catch (Exception e) {
                            System.out.println("Error al crear la factura: " + e.getMessage());
                            return true;
                        }

                        // Imprimir factura
                        String facturaStr = printInvoiceUseCase.print(invoice);
                        System.out.println(facturaStr);
                    } catch (Exception e) {
                        System.out.println("Error en facturación: " + e.getMessage());
                    }
                    return true;
                }

                case "3": {
                    try {
                        System.out.println("--- FACTURACIÓN ---");
                        // Solicitar documento del paciente
                        System.out.print("Ingrese cédula del paciente: ");
                        String docPatientStr = reader.nextLine();
                        Long docPatient = null;
                        try {
                            docPatient = Long.parseLong(docPatientStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Cédula inválida. Debe ser numérica.");
                            return true;
                        }

                        // Buscar paciente
                        Patient patientQuery = new Patient();
                        patientQuery.setDocument(docPatient);
                        Patient patient = userPortPatient.findByDocument(patientQuery);
                        if (patient == null) {
                            System.out.println("Paciente no encontrado.");
                            return true;
                        }

                        // Solicitar documento del doctor
                        System.out.print("Ingrese cédula del doctor: ");
                        String docDoctorStr = reader.nextLine();
                        Long docDoctor = null;
                        try {
                            docDoctor = Long.parseLong(docDoctorStr);
                        } catch (NumberFormatException e) {
                            System.out.println("Cédula inválida. Debe ser numérica.");
                            return true;
                        }

                        // Buscar doctor como usuario y validar rol
                        User doctor = null;
                        try {
                            User doctorQuery = new User();
                            doctorQuery.setDocument(docDoctor);
                            doctor = userPortOut.findByDocument(doctorQuery);
                            if (doctor == null || doctor.getRole() == null || !doctor.getRole().equals(Role.DOCTOR)) {
                                System.out.println("Doctor no encontrado o no tiene rol de DOCTOR.");
                                return true;
                            }
                        } catch (Exception e) {
                            System.out.println("Error buscando doctor: " + e.getMessage());
                            return true;
                        }

                        // Buscar doctor en la tabla doctors y usar su id real
                        Long doctorId = null;
                        String doctorName = null;
                        Optional<DoctorEntity> doctorEntityOpt = doctorRepository.findByDocument(docDoctor);
                        if (doctorEntityOpt.isPresent()) {
                            doctorId = doctorEntityOpt.get().getId();
                            doctorName = doctorEntityOpt.get().getName();
                        } else {
                            System.out.println("El doctor no está registrado en la tabla de médicos.");
                            return true;
                        }
                        // Asignar el id correcto al objeto doctor
                        doctor.setId(doctorId);
                        doctor.setNameComplete(doctorName);

                        // Buscar póliza activa del paciente
                        Policy policy = null;
                        try {
                            java.util.List<Policy> policies = policyPort.findByPatient(patient);
                            if (policies != null && !policies.isEmpty()) {
                                policy = policies.stream().filter(Policy::isPolicyStatus).findFirst().orElse(null);
                            }
                        } catch (Exception e) {
                            System.out.println("Error buscando póliza: " + e.getMessage());
                        }

                        // Solicitar monto total de servicios
                        System.out.print("Ingrese el valor total de los servicios: $");
                        String amountStr = reader.nextLine();
                        double amount = 0;
                        try {
                            amount = Double.parseDouble(amountStr);
                            if (amount <= 0) {
                                System.out.println("El monto debe ser mayor que 0.");
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Monto inválido. Debe ser numérico.");
                            return true;
                        }

                        // Crear factura
                        Invoice invoice = null;
                        try {
                            invoice = createInvoiceUseCase.execute(patient, doctor, policy, amount);
                        } catch (Exception e) {
                            System.out.println("Error al crear la factura: " + e.getMessage());
                            return true;
                        }

                        // Imprimir factura
                        String facturaStr = printInvoiceUseCase.print(invoice);
                        System.out.println(facturaStr);
                    } catch (Exception e) {
                        System.out.println("Error en facturación: " + e.getMessage());
                    }
                    return true;
                }

                case "4": {

                    policyClient.session();
                    return true;
                }

                case "5": {
                    System.out.println("Saliendo de Adminitrador...");
                    return false;
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

    private Patient readInfoFromPatient() throws Exception {

        System.out.println("Ingrese nombre: ");
        String nameComplete = reader.nextLine();
        System.out.println("Ingrese apellido: ");
        String lastnameComplete = reader.nextLine();

        Long document = null;
        while (document == null) {
            System.out.println("Ingrese cedula: ");
            String docStr = reader.nextLine();
            try {
                document = Long.parseLong(docStr);
            } catch (NumberFormatException e) {
                System.out.println("Cédula inválida. Ingrese solo números, intente de nuevo.");
            }
        }

        Integer age = null;
        while (age == null) {
            System.out.println("Ingrese edad: ");
            String ageStr = reader.nextLine();
            try {
                age = Integer.parseInt(ageStr);
                if (age < 0 || age > 150) {
                    System.out.println("Edad inválida");
                    age = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Edad inválida. Ingrese un número entero.");
            }
        }

        System.out.println("Ingrese genero: ");
        String gender = reader.nextLine();
        System.out.println("Ingrese direccion: ");
        String address = reader.nextLine();

        // Solicitar teléfono (10 dígitos)
        String phoneNumber = null;
        while (phoneNumber == null) {
            System.out.println("Ingrese número de teléfono (10 dígitos): ");
            String input = reader.nextLine();
            if (input.matches("^\\d{10}$")) {
                phoneNumber = input;
            } else {
                System.out.println("Número inválido. Debe tener exactamente 10 dígitos.");
            }
        }

        // Solicitar correo electrónico (opcional)
        System.out.println("Ingrese correo electrónico (opcional): ");
        String email = reader.nextLine();

        System.out.println("Datos de contacto.");
        System.out.println("Ingrese nombre contacto de emergencia: ");
        String contactName = reader.nextLine();
        System.out.println("Ingrese que relacion tiene con el paciente: ");
        String relationship = reader.nextLine();
        System.out.println("Ingrese numero contacto de emergencia: ");
        String contactNumber = reader.nextLine();

        return userBuilder.buildAdmin(
                nameComplete,
                lastnameComplete,
                document,
                age,
                gender,
                address,
                contactName,
                relationship,
                contactNumber,
                phoneNumber,
                email);
    }

    private Appointment readInfoFromAppointment() throws Exception {
        System.out.println("Ingrese documento de admin: ");
        String documentAdmin = reader.nextLine();
        System.out.println("Ingrese documento de paciente: ");
        String documentPatient = reader.nextLine();

        return appointmentBuilder.appointmentBuilder(documentAdmin, documentPatient);
    }

}