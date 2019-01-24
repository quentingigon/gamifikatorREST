package io.swagger.repositories;

import io.swagger.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity getByName(String name);

	List<UserEntity> getEndUserEntitiesByApiToken(String apiToken);

	UserEntity getById(Long id);
}
