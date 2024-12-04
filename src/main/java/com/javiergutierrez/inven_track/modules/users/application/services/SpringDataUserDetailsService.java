package com.javiergutierrez.inven_track.modules.users.application.services;

import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UserEntity;
import com.javiergutierrez.inven_track.modules.users.infrastructure.repositories.IJpaUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

	private final IJpaUserRepository userRepository;

	public SpringDataUserDetailsService(IJpaUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.map(this::map)
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

	private UserDetails map(UserEntity user) {
		return org.springframework.security.core.userdetails.User.withUsername(
						user.getUsername())
				.password(user.getPassword())
				.roles(String.valueOf(user.getRole()))
				.build();
	}
}
