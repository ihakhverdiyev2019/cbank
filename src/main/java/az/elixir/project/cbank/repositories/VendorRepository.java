package az.elixir.project.cbank.repositories;

import az.elixir.project.cbank.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<VendorModel, UUID> {
}
