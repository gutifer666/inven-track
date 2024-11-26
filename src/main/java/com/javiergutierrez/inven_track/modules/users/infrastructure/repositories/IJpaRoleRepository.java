package com.javiergutierrez.inven_track.modules.users.infrastructure.repositories;

import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IJpaRoleRepository  extends JpaRepository<RoleEntity, Long>, JpaSpecificationExecutor<RoleEntity> {
}
