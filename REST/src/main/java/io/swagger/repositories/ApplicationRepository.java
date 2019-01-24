package io.swagger.repositories;

import io.swagger.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

@Repository
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {

	@Lock(LockModeType.OPTIMISTIC)
	@Transactional
	ApplicationEntity findByApiToken(String apiToken);
}
