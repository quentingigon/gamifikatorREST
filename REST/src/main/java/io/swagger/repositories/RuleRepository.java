package io.swagger.repositories;

import io.swagger.entities.RuleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends CrudRepository<RuleEntity, Long> {

	RuleEntity getByName(String name);

	RuleEntity getByNameAndApiToken(String name, String apiToken);

	List<RuleEntity> getRuleEntitiesByApiToken(String apiToken);

	List<RuleEntity> getByApiTokenAndBadgeId(String name, Long badgeId);


}
