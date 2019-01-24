package io.swagger.repositories;

import io.swagger.entities.PropertyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

	PropertyEntity getByNameAndApiToken(String name, String apiToken);
}
