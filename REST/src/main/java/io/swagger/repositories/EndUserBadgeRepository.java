package io.swagger.repositories;

import io.swagger.entities.EndUserBadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserBadgeRepository extends CrudRepository<EndUserBadgeEntity, String> {

}
