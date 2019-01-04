package io.swagger.repositories;

import io.swagger.entities.EndUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface EndUserRepository extends CrudRepository<EndUserEntity, String> {
}
