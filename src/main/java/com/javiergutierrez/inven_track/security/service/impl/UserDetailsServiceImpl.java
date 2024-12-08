package com.javiergutierrez.inven_track.security.service.impl;

import com.javiergutierrez.inven_track.security.entity.UserEntity;
import com.javiergutierrez.inven_track.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final UserEntity systemMember = userRepository.findByName(username);
		if (systemMember == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}
		return User.withUsername(systemMember.getName())
				.password(systemMember.getPassword())
				.authorities("AUTH_ADMIN")
				.accountExpired(false)
				.accountLocked(false)
				.disabled(false)
				.roles("ADMIN")
				.build();
	}
}
