package com.javiergutierrez.inven_track.security.service.impl;

import com.javiergutierrez.inven_track.security.entity.UserEntity;
import com.javiergutierrez.inven_track.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

	private final UserRepository userRepository;

	public UserEntity create(UserEntity user) {
		return userRepository.save(user);
	}
}
