package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
