package app.application.usecases;

import app.infrastructure.persistence.entities.MedicalOrderEntity;

import java.util.List;
import java.util.Scanner;

/**
 * Helper para manejar selección interactiva de órdenes cuando la búsqueda por
 * número devuelve múltiples resultados.
 */
public final class SelectOrderHelper {

    private SelectOrderHelper() {
    }

    public static MedicalOrderEntity chooseOrder(List<MedicalOrderEntity> orders, Scanner reader) {
        if (orders == null || orders.isEmpty())
            return null;
        if (orders.size() == 1)
            return orders.get(0);

        System.out.println("Se encontraron múltiples órdenes con ese número:");
        for (MedicalOrderEntity o : orders) {
            String idStr = o.getId() == null ? "(sin id)" : String.valueOf(o.getId());
            System.out.println("- ID: " + idStr + " | Orden: " + safe(o.getOrderNumber()) + " | Paciente documento: "
                    + safe(o.getPatientDocument()) + " | Fecha: " + safe(o.getCreatedAt()));
        }

        System.out.print("Ingrese el ID de la orden que desea usar (o pulse Enter para cancelar): ");
        String input = reader.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Operación cancelada por el usuario.");
            return null;
        }

        try {
            Long chosenId = Long.parseLong(input);
            for (MedicalOrderEntity o : orders) {
                if (o.getId() != null && o.getId().equals(chosenId)) {
                    return o;
                }
            }
            System.out.println("No se encontró una orden con ID " + chosenId + ". Operación cancelada.");
            return null;
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Operación cancelada.");
            return null;
        }
    }

    private static String safe(Object v) {
        return v == null ? "n/a" : v.toString();
    }
}
