package com.javiergutierrez.inven_track.modules.users.application.usecases.user.queries;

import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UserRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class RetrieveUserUseCase {

	private UserRepositoryAdapter userRepositoryAdapter;

	public Optional<List<User>> findAllUsers() {
		log.info("Call to findAllUsers.");

		Optional<List<User>> userList = userRepositoryAdapter.findAllUsers();

		log.debug(userList.toString());

		return userList;
	}

	public Optional<User> findUserById(Long id) {
		log.info("Call to findUserById.");
		return userRepositoryAdapter.findUserById(id);
	}

}
