
package app.adapter.out;

import org.springframework.stereotype.Component;
import app.domain.ports.NursesPortOut;
import app.domain.model.*;
import app.infrastructure.persistence.mapper.NursesMapper;
import app.infrastructure.persistence.repository.*;

@Component
public class NursesJpaAdapter implements NursesPortOut {

    private final VitalSignsRepository vitalSignsRepository;
    private final ProcedureRepository procedureRepository;
    private final ObservationRepository observationRepository;
    private final NursesMapper mapper;

    public NursesJpaAdapter(VitalSignsRepository vitalSignsRepository,
                            ProcedureRepository procedureRepository,
                            ObservationRepository observationRepository,
                            NursesMapper mapper) {
        this.vitalSignsRepository = vitalSignsRepository;
        this.procedureRepository = procedureRepository;
        this.observationRepository = observationRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveVitalSigns(VitalSignsRecord record) {
        vitalSignsRepository.save(mapper.toVitalSignsEntity(record));
    }

    @Override
    public void saveProcedure(Procedure procedure) {
        procedureRepository.save(mapper.toProcedureEntity(procedure));
    }

    @Override
    public void saveObservation(Observation observation) {
        observationRepository.save(mapper.toObservationEntity(observation));
    }
}
