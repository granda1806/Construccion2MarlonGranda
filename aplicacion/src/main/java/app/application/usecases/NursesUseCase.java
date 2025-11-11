package app.application.usecases;

import org.springframework.stereotype.Service;
import app.domain.ports.NursesPortIn;
import app.domain.ports.NursesPortOut;
import app.domain.model.VitalSignsRecord;
import app.domain.model.Procedure;
import app.domain.model.Observation;

@Service
public class NursesUseCase implements NursesPortIn {

    private final NursesPortOut nursesPortOut;

    public NursesUseCase(NursesPortOut nursesPortOut) {
        this.nursesPortOut = nursesPortOut;
    }

    @Override
    public void registrarSignosVitales(VitalSignsRecord record) {
        nursesPortOut.saveVitalSigns(record);
    }

    @Override
    public void registrarProcedimiento(Procedure procedure) {
        nursesPortOut.saveProcedure(procedure);
    }

    @Override
    public void registrarObservacion(Observation observation) {
        nursesPortOut.saveObservation(observation);
    }
    
}
