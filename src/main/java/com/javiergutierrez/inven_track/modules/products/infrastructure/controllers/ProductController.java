package com.javiergutierrez.inven_track.modules.products.infrastructure.controllers;

import com.javiergutierrez.inven_track.modules.products.application.services.ProductService;
import com.javiergutierrez.inven_track.modules.products.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@Slf4j
@Validated
@RestController
public class ProductController {

	private final ProductService userService;

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id) {

		return ResponseEntity.ok(userService.findProductById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable Long id) {

		return ResponseEntity.ok(userService.deleteProduct(id));
	}

/*	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		log.info("Call to createUser {}", user);
		User createdUser = userService.createUser(user).orElseThrow(() -> {
			log.error("Failed to create user {}", user);
			return new IllegalStateException("Failed to create user.");
		});
		log.info("User created {}", createdUser);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		log.info("Call to findUserById with ID: {}", id);
		User user = userService.findUserById(id).orElseThrow(() -> {
			log.error("Failed to get user with id: {}", id);
			return new IllegalStateException("Failed to find user with ID: " + id);
		});
		log.info("Successfully retrieved user: {}.", user);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		log.info("Call to findAllUsers.");
		List<User> userList = userService.findAllUsers().orElseThrow(() -> {
			log.error("Failed to find users.");
			return new IllegalStateException("Failed to find users.");
		});
		log.info("Successfully retrieved users: {}.", userList);
		return ResponseEntity.ok(userList);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		log.info("Call to updateUser with id {}.", id);
		User updatedUser = userService.updateUser(id, user).orElseThrow(() -> {
			log.error("Failed to update user with id: {}", id);
			return new IllegalStateException("Failed to update user with ID: " + id);
		});
		log.info("Successfully updated user: {}.", updatedUser);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		log.info("Call to deleteUser with id {}.", id);
		User deletedUser = userService.deleteUser(id).orElseThrow(() -> {
			log.error("Failed to delete user with id: {}", id);
			return new IllegalStateException("Failed to delete user with ID: " + id);
		});
		log.info("Successfully deleted user: {}.", deletedUser);
		return ResponseEntity.ok(deletedUser);
	}*/

}
