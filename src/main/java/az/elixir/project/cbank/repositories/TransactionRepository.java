package az.elixir.project.cbank.repositories;

import az.elixir.project.cbank.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {
}
