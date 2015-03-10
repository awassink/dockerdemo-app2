package app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Credit Request repository
 */

//https://github.com/spring-projects/spring-data-rest/wiki/Configuring-the-REST-URL-path
@RestResource(exported = false)
public interface CreditRequestRepository extends CrudRepository<CreditRequest, Long> {

}
