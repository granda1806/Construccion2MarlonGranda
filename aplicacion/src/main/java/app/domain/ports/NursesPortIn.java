
package app.domain.ports;

import app.domain.model.VitalSignsRecord;
import app.domain.model.Procedure;
import app.domain.model.Observation;

public interface NursesPortIn {
    void registrarSignosVitales(VitalSignsRecord record);
    void registrarProcedimiento(Procedure procedure);
    void registrarObservacion(Observation observation);
}