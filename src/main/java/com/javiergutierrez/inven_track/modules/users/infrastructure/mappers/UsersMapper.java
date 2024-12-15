package com.javiergutierrez.inven_track.modules.users.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.users.domain.Users;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UsersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper extends GenericMapper<UsersEntity, Users> {
}
