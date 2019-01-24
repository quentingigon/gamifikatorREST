package io.swagger.repositories;

import io.swagger.entities.UserBadgesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBadgesRepository extends CrudRepository<UserBadgesEntity, Long> {

	List<UserBadgesEntity> findUserBadgesEntitiesByUserId(Long userId);

}
