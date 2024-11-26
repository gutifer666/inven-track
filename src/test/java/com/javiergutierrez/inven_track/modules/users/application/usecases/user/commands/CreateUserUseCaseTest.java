package com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands;

import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UserRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {
	@Mock
	private UserRepositoryAdapter userRepositoryAdapter;
	@InjectMocks
	private CreateUserUseCase createUserUseCase;

	private static AutoCloseable closable;
	private static User user;
	private User expectedUser;

	@BeforeEach
	void beforeEach() {
		log.info("Start to prepare beforeEach");

		Role role = Role.builder()
				.id(1L)
				.name("Admin")
				.build();

		user = User.builder()
				.id(1L)
				.username("jdoe")
				.password("password123")
				.email("admin@gmail.com")
				.role(role)
				.build();

		expectedUser = user.clone();

		log.info("End to BeforeEach.");
	}

	@Test
	void when_create_user_should_be_a_new_user() {
		String method = "when_create_user_should_be_a_new_user";
		log.info("Start to test: {}", method);

		when(userRepositoryAdapter.createUser(user)).thenReturn(Optional.of(expectedUser));

		Optional<User> resultOptional = createUserUseCase.createUser(user);
		assertTrue(resultOptional.isPresent(), "Result should be present.");
		User result = resultOptional.get();

		verify(userRepositoryAdapter).createUser(user);

		assertUser(result);

		log.info("End {}.", method);
	}

	@Test
	void when_create_user_fails_should_throw_exception() {
		String method = "when_create_user_fails_should_throw_exception";
		log.info("Start {}.", method);

		when(userRepositoryAdapter.createUser(user)).thenReturn(Optional.empty());

		assertThrows(IllegalStateException.class, () -> createUserUseCase.createUser(user));

		verify(userRepositoryAdapter).createUser(user);

		log.info("End {}.", method);
	}

	private void assertUser(User result) {
		assertNotNull(result, "Result should not be null.");
		assertEquals(expectedUser.getId(), result.getId(), "Id should be the same.");
		assertEquals(expectedUser.getUsername(), result.getUsername(), "Username should be the same.");
		assertEquals(expectedUser.getPassword(), result.getPassword(), "Password should be the same.");
		assertEquals(expectedUser.getEmail(), result.getEmail(), "Email should be the same.");
		assertEquals(expectedUser.getRole(), result.getRole(), "Role should be the same.");
	}
}