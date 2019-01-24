package io.swagger.repositories;

import io.swagger.entities.UserEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	UserEntity getByName(String name);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	List<UserEntity> getEndUserEntitiesByApiToken(String apitoken);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	UserEntity getById(Long id);
}