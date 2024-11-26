package com.javiergutierrez.inven_track.modules.users.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<RoleEntity, Role> {
}
