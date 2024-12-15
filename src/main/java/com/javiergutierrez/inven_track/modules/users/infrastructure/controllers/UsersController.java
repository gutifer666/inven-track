package com.javiergutierrez.inven_track.modules.users.infrastructure.controllers;

import com.javiergutierrez.inven_track.modules.users.application.services.UsersService;
import com.javiergutierrez.inven_track.modules.users.domain.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
@Validated
@RestController
public class UsersController {

	private final UsersService usersService;

	@PostMapping
	public ResponseEntity<Users> createUsers(@Valid @RequestBody Users users) {
		log.info("Call to createUsers {}", users);
		Users createdUsers = usersService.createUsers(users).orElseThrow(() -> {
			log.error("Failed to create users {}", users);
			return new IllegalStateException("Failed to create users.");
		});
		log.info("Users created {}", createdUsers);
		return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Users> findUsersById(@PathVariable Long id) {
		log.info("Call to findUsersById with ID: {}", id);
		Users users = usersService.findUsersById(id).orElseThrow(() -> {
			log.error("Failed to get users with id: {}", id);
			return new IllegalStateException("Failed to find users with ID: " + id);
		});
		log.info("Successfully retrieved users: {}.", users);
		return ResponseEntity.ok(users);
	}

	@GetMapping
	public ResponseEntity<List<Users>> findAllUsers() {
		log.info("Call to findAllUsers.");
		List<Users> usersList = usersService.findAllUsers().orElseThrow(() -> {
			log.error("Failed to find users.");
			return new IllegalStateException("Failed to find users.");
		});
		log.info("Successfully retrieved users: {}.", usersList);
		return ResponseEntity.ok(usersList);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Users> updateUsers(@PathVariable Long id, @Valid @RequestBody Users users) {
		log.info("Call to updateUsers with id {}.", id);
		Users updatedUsers = usersService.updateUsers(id, users).orElseThrow(() -> {
			log.error("Failed to update users with id: {}", id);
			return new IllegalStateException("Failed to update users with ID: " + id);
		});
		log.info("Successfully updated users: {}.", updatedUsers);
		return ResponseEntity.ok(updatedUsers);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Users> deleteUsers(@PathVariable Long id) {
		log.info("Call to deleteUsers with id {}.", id);
		Users deletedUsers = usersService.deleteUsers(id).orElseThrow(() -> {
			log.error("Failed to delete users with id: {}", id);
			return new IllegalStateException("Failed to delete users with ID: " + id);
		});
		log.info("Successfully deleted users: {}.", deletedUsers);
		return ResponseEntity.ok(deletedUsers);
	}
}
