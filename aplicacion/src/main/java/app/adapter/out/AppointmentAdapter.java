package app.adapter.out;

import app.domain.model.Appointment;
import app.domain.ports.AppointmentPort;
import app.infrastructure.persistence.entities.AppointmentEntity;
import app.infrastructure.persistence.mapper.AppointmentMapper;
import app.infrastructure.persistence.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppointmentAdapter implements AppointmentPort
{
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Override
    public Appointment findById(Appointment appointment) throws Exception
    {
        
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(appointment.getId());
        
        return AppointmentMapper.toDomain(appointmentEntity);
        
    }
    
    @Override
    public void save(Appointment appointment) throws Exception
    {
        
        appointmentRepository.save(AppointmentMapper.toEntity(appointment));
        System.out.println("El administrador " + appointment.getAdmin().getNameComplete() +
                " ha agendado la cita del paciente " + appointment.getPatient().getNameComplete() + " con fecha: " + appointment.getDate());
    }
    
}
