package app.adapter.out;

import app.domain.model.Appointment;
import app.domain.model.Patient;
import app.domain.ports.AppointmentPort;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.mapper.AppointmentMapper;
import app.infrastructure.persistence.mapper.PatientMapper;
import app.infrastructure.persistence.repository.AppointmentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentAdapter implements AppointmentPort
{
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Override
    public Appointment findById(Appointment appointment) throws Exception
    {
        
        AppointmentEntity appointmentEntity = appointmentRepository.findById(appointment.getId());
        
        return AppointmentMapper.toDomain(appointmentEntity);
        
    }
    
    @Override
    public List<Appointment> findByDocumentPatient(Patient patient) throws Exception
    {
		
        List<Appointment> appointments = new ArrayList<Appointment>();
		
                List<AppointmentEntity> appointmentEntities = appointmentRepository.findByPatient(PatientMapper.toEntity(patient));
		
                for (AppointmentEntity entity : appointmentEntities)
                {
                    
			appointments.add(AppointmentMapper.toDomain(entity));
                        
		}
                
		return appointments;
                
	}
    
    @Override
    public void save(Appointment appointment) throws Exception
    {
        
        appointmentRepository.save(AppointmentMapper.toEntity(appointment));
        System.out.println("El administrador " + appointment.getAdmin().getNameComplete() +
                " ha agendado la cita del paciente " + appointment.getPatient().getNameComplete() + " correctamente");  
        
    }
    
}
