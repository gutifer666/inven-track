package com.javiergutierrez.inven_track.modules.users.application.services;


import com.javiergutierrez.inven_track.modules.users.application.usecases.CreateUsersUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.DeleteUsersUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.RetrieveUsersUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.UpdateUsersUseCase;
import com.javiergutierrez.inven_track.modules.users.domain.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UsersService {
	private CreateUsersUseCase createUsersUseCase;
	private RetrieveUsersUseCase retrieveUsersUseCase;
	private UpdateUsersUseCase updateUsersUseCase;
	private DeleteUsersUseCase deleteUsersUseCase;

	public Optional<Users> createUsers(Users users) {
		log.info("Call to createUsers.");
		log.debug(users.toString());
		return createUsersUseCase.createUsers(users);
	}

	public Optional<Users> findUsersById(Long id) {
		log.info("Call to findUsersById with id {}.", id);
		return retrieveUsersUseCase.findUsersById(id);
	}

	public Optional<Users> updateUsers(Long id, Users users) {
		log.info("Call to updateUsers with id {}.", id);
		log.debug(users.toString());
		return updateUsersUseCase.updateUsers(id, users);
	}

	public Optional<Users> deleteUsers(Long id) {
		log.info("Call to deleteUsers with id {}.", id);
		return deleteUsersUseCase.deleteUsers(id);
	}

	public Optional<List<Users>> findAllUsers() {
		log.info("Call to findAllUsers.");
		return retrieveUsersUseCase.findAllUsers();
	}
}
