
package app.adapter.out;

import app.domain.model.Person;
import app.domain.ports.UserPortPatient;
import app.infrastructure.persistence.entities.PatientEntity;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientAdapter implements UserPortPatient{
    @Autowired
    private PatientRepository patientRepository;
    
    @Override
    public Person findByDocument(Person user) throws Exception {
        PatientEntity patientEntity = patientRepository.findById(user.getId());
        return PatientMapper.toDomain(patientEntity);
 
    }
    
    @Override
    public Person findByName(Person user) throws Exception
    {
        return null;   
    }
    
    @Override
    public void save(Person user) throws Exception {
        patientRepository.save(PatientMapper.toEntity(user));
        System.out.println("Se ha creado el paciente.");  
    }
}
