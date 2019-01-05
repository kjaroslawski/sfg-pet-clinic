package pl.qamar.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.qamar.sfgpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
