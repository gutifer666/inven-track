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
public class DeleteUsersUseCase {

	private UsersRepositoryAdapter usersRepositoryAdapter;

	public Optional<Users> deleteUsers(long usersId) {
		log.info("Call to deleteUsers {}", usersId);
		boolean isDeleted = usersRepositoryAdapter.deleteUsers(usersId);
		if (!isDeleted) {
			throw new IllegalStateException("Failed to delete users with ID: " + usersId);
		}
		return Optional.empty();
	}

}
