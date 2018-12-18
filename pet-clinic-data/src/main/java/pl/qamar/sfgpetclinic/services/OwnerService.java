package pl.qamar.sfgpetclinic.services;

import pl.qamar.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
