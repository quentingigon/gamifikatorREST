package io.swagger.repositories;

import io.swagger.entities.UserBadgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBadgeRepository extends CrudRepository<UserBadgeEntity, String> {

}
