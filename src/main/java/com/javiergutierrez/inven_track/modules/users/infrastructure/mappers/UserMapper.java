package com.javiergutierrez.inven_track.modules.users.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserEntity, User> {
}
