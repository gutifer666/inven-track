package com.javiergutierrez.inven_track.modules.users.infrastructure.adapters;


import com.javiergutierrez.inven_track.modules.users.domain.Users;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UsersEntity;
import com.javiergutierrez.inven_track.modules.users.infrastructure.mappers.UsersMapper;
import com.javiergutierrez.inven_track.modules.users.infrastructure.repositories.IJpaUsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Component
public class UsersRepositoryAdapter {

	private final IJpaUsersRepository iJpaUsersRepository;
	private final UsersMapper usersMapper;

	public Optional<Users> createUsers(Users users) {
		log.info("Call to createUsers {}", users);
		log.debug("Users to create: {}", users);
		UsersEntity usersEntity = usersMapper.toEntity(users);
		UsersEntity createdUsersEntity = iJpaUsersRepository.save(usersEntity);
		log.info("Created users with ID: {}", users.getId());
		log.debug("Users created: {}", createdUsersEntity);
		return Optional.of(usersMapper.toModel(createdUsersEntity));
	}

	public Optional<List<Users>> findAllUsers() {
		log.info("Call to findAllUsers.");
		List<Users> usersList = iJpaUsersRepository.findAll().stream()
				.map(usersMapper::toModel)
				.collect(Collectors.toList());
		log.info("Found {} users.", usersList.size());
		log.debug("Users found: {}", usersList);
		return Optional.of(usersList);
	}

	public Optional<Users> findUsersById(Long id) {
		log.info("Call to findUsersById with ID: {}.", id);
		Optional<Users> users = iJpaUsersRepository.findById(id)
				.map(usersMapper::toModel);
		if (users.isPresent()) {
			log.info("Found users with ID: {}.", id);
		} else {
			log.error("No users found with ID: {}.", id);
		}
		return users;
	}

	public Optional<Users> updateUsers(Users users) {
		log.info("Call to updateUsers with ID: {}.", users.getId());
		UsersEntity productEntity = usersMapper.toEntity(users);
		UsersEntity updatedUsersEntity = iJpaUsersRepository.save(productEntity);
		log.info("Updated users with ID: {}.", users.getId());
		return Optional.of(usersMapper.toModel(updatedUsersEntity));
	}

	public boolean deleteUsers(Long id) {
		log.info("Call to deleteUsers with ID: {}.", id);
		if (iJpaUsersRepository.existsById(id)) {
			iJpaUsersRepository.deleteById(id);
			log.info("Deleted users with ID: {}.", id);
			return true;
		} else {
			log.error("No users found with ID: {}.", id);
			return false;
		}
	}
}
