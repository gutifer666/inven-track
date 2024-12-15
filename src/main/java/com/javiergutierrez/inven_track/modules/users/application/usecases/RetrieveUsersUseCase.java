package com.javiergutierrez.inven_track.modules.users.application.usecases;

import com.javiergutierrez.inven_track.modules.users.domain.Users;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UsersRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class RetrieveUsersUseCase {

	private UsersRepositoryAdapter usersRepositoryAdapter;

	public Optional<List<Users>> findAllUsers() {
		log.info("Call to findAllUsers.");

		Optional<List<Users>> usersList = usersRepositoryAdapter.findAllUsers();

		log.debug(usersList.toString());

		return usersList;
	}

	public Optional<Users> findUsersById(Long id) {
		log.info("Call to findUsersById.");
		return usersRepositoryAdapter.findUsersById(id);
	}
}
