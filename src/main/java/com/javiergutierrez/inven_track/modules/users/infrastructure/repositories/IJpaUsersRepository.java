package com.javiergutierrez.inven_track.modules.users.infrastructure.repositories;

import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaUsersRepository extends JpaRepository<UsersEntity, Long> {
	UsersEntity findByUsername(String username);
}
