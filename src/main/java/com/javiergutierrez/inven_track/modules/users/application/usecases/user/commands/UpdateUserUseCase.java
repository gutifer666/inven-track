package com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands;

import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UserRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Transactional
@Component
public class UpdateUserUseCase {

	private UserRepositoryAdapter userRepositoryAdapter;

	public Optional<User> updateUser(Long id, User user) {
		log.info("Call to updateUser with ID: {}.", id);
		log.debug(user.toString());

		User existingUser = userRepositoryAdapter.findUserById(id)
				.orElseThrow(() -> new IllegalStateException("Failed to get user with ID: " + id));

		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());
		existingUser.setEmail(user.getEmail());
		existingUser.getRole().setId(user.getRole().getId());

		log.debug("User after update. {}", existingUser);

		return userRepositoryAdapter.updateUser(existingUser);
	}
}
