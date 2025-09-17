package app.infrastructure.persistence.repository;

import app.infrastructure.persistence.mapper.UserMapper;
import app.infrastructure.persistence.entities.UserEntity;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryAdapter extends JpaRepository<UserEntity, Long> {

    public UserEntity findByDocument(long document);

    public UserEntity findByUserName(String userName);
    

 /*   @Override
    public app.domain.model.User findByDocument(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public app.domain.model.User findByName(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(app.domain.model.User user) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
