package io.swagger.repositories;

import io.swagger.entities.AppEndUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface AppEndUserRepository extends CrudRepository<AppEndUserEntity, String> {
}
