package io.swagger.repositories;

import io.swagger.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {

	ApplicationEntity findByApiToken(String apiToken);
}
