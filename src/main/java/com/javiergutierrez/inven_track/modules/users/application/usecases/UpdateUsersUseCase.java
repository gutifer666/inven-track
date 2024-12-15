package com.javiergutierrez.inven_track.modules.users.application.usecases;

import com.javiergutierrez.inven_track.modules.users.domain.Users;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UsersRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Transactional
@Component
public class UpdateUsersUseCase {

	private UsersRepositoryAdapter usersRepositoryAdapter;

	public Optional<Users> updateUsers(Long id, Users users) {
		log.info("Call to updateUsers with ID: {}.", id);
		log.debug(users.toString());

		Users existingUsers = usersRepositoryAdapter.findUsersById(id)
				.orElseThrow(() -> new IllegalStateException("Failed to get users with ID: " + id));

		existingUsers.setUsername(users.getUsername());
		existingUsers.setPassword(users.getPassword());

		log.debug("Users after update. {}", existingUsers);

		return usersRepositoryAdapter.updateUsers(existingUsers);

	}

}
