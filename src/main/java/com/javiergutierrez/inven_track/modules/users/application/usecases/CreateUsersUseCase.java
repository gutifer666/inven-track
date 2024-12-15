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
public class CreateUsersUseCase {

	private UsersRepositoryAdapter usersRepositoryAdapter;

	public Optional<Users> createUsers(Users users) {
		log.info("Call to createUsers {}", users);
		log.debug("Users to create: {}", users);
		Optional<Users> createdUsers = usersRepositoryAdapter.createUsers(users);
		if (createdUsers.isEmpty()) {
			throw new IllegalStateException("Failed to create users: " + users);
		}
		return createdUsers;
	}

}
