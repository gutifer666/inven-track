package com.javiergutierrez.inven_track.modules.users.infrastructure.repositories;

import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IJpaUserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailIn(List<String> emailList);
}
