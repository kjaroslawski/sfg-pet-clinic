package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
