/*
package com.javiergutierrez.inven_track.modules.users.application.services;

import com.javiergutierrez.inven_track.modules.users.domain.UserPrincipal;
import com.javiergutierrez.inven_track.modules.users.domain.Users;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UsersEntity;
import com.javiergutierrez.inven_track.modules.users.infrastructure.mappers.UsersMapper;
import com.javiergutierrez.inven_track.modules.users.infrastructure.repositories.IJpaUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsersMapper usersMapper;

	@Autowired
	public IJpaUsersRepository usersRepository;

	public UserDetailsServiceImpl(UsersMapper usersMapper) {
		this.usersMapper = usersMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsersEntity usersEntity = usersRepository.findByUsername(username);

		if (usersEntity == null) {
			log.warn("User not found: {}", username);
			throw new UsernameNotFoundException("user not found:");
		}

		Users users = usersMapper.toModel(usersEntity);

		return new UserPrincipal(users);
	}
}
*/
