package app.infrastructure.persistence.repository;

import app.domain.model.*;
import java.util.*;

public interface NursesRepository {
    Optional<Person> findPatientByDocument(long document);
    Optional<MedicalOrder> findOrderByNumber(String orderNumber);
    void saveVitalSigns(VitalSignsRecord record);
}
