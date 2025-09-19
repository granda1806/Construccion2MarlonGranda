
package app.application.usecases;

import app.adapter.in.client.HResourcesClient;
import app.domain.model.enums.Role;
import app.domain.services.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalUseCase {
    @Autowired
    private CreateUser createUser;
    
    //aqui agregar crear orden clinica, buscar orden clinica y crear historial clinica. De momento solo usare user
    
    public void createUSer(HResourcesClient user) throws Exception{
        user.session();
    }
    
}
