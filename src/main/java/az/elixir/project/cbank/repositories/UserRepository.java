package az.elixir.project.cbank.repositories;

import az.elixir.project.cbank.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

        UserModel findByCustomerCode(String customerCode);
        UserModel findByEmailAndPassword(String email, String password);

}
