package guru.springframework.spring6restmvc.repositories;

import guru.springframework.spring6restmvc.entities.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
//Remember the Jpa take in two Generic types, the first is the entity type -> an entity class is a class
// the instances of the entity classes represent records in the database table.
// and the second is the type of the id
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID>{



}
