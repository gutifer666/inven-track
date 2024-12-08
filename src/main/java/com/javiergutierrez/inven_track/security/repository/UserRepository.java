package com.javiergutierrez.inven_track.security.repository;

import com.javiergutierrez.inven_track.security.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByName(String username);
}
