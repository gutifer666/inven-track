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
public class DeleteUserUseCase {

	private UserRepositoryAdapter userRepositoryAdapter;

	public Optional<User> deleteUser(long userId) {
		log.info("Call to deleteUser {}", userId);
		boolean isDeleted = userRepositoryAdapter.deleteUser(userId);
		if (!isDeleted) {
			throw new IllegalStateException("Failed to delete user with ID: " + userId);
		}
		return Optional.empty();
	}
}
