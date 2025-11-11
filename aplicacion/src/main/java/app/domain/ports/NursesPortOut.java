
package app.domain.ports;

import app.domain.model.VitalSignsRecord;
import app.domain.model.Procedure;
import app.domain.model.Observation;

public interface NursesPortOut {
    void saveVitalSigns(VitalSignsRecord record);
    void saveProcedure(Procedure procedure);
    void saveObservation(Observation observation);
}