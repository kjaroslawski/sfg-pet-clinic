package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
