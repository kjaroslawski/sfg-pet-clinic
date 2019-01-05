package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.Speciality;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
