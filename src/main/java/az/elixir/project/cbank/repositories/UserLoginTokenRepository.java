package az.elixir.project.cbank.repositories;

import az.elixir.project.cbank.models.UserLoginTokenModel;
import az.elixir.project.cbank.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginTokenRepository extends JpaRepository<UserLoginTokenModel,Integer> {

    UserLoginTokenModel findByUser(UserModel userModel);
    UserLoginTokenModel findByToken(String token);
}
