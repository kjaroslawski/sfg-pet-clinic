package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
