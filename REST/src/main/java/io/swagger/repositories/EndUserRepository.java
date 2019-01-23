package io.swagger.repositories;

import io.swagger.entities.EndUserEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EndUserRepository extends CrudRepository<EndUserEntity, String> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	EndUserEntity getByName(String name);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	List<EndUserEntity> getEndUserEntitiesByApiToken(String apiToken);

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	EndUserEntity getById(Long id);
}
