package security.springframework.repositories;

import org.springframework.stereotype.Repository;
import security.springframework.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{

}
