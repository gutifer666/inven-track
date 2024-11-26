package com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands;

import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.adapters.UserRepositoryAdapter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class DeleteUserUseCaseTest {

	@Mock
	private UserRepositoryAdapter userRepositoryAdapter;
	@InjectMocks
	private DeleteUserUseCase deleteUserUseCase;

	private static AutoCloseable closable;
	private Role role;
	private User user;

	@BeforeEach
	void beforeEach() {
		log.info("Start to prepare beforeEach");

		closable = MockitoAnnotations.openMocks(this);

		role = Role.builder()
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

		log.info("End to BeforeEach.");
	}

	@AfterAll
	static void afterAll() throws Exception {
		log.info("Start to AfterAll");
		closable.close();
		log.info("\tIDeleteUserUseCaseImplTest: Finalizing tests");
	}

	@Test
	void when_delete_user_should_be_deleted() {
		String method = "when_delete_user_should_be_deleted";
		log.info("Start {}.", method);

		when(userRepositoryAdapter.deleteUser(user.getId())).thenReturn(true);

		Optional<User> resultOptional = deleteUserUseCase.deleteUser(user.getId());

		assertTrue(resultOptional.isEmpty(), "Result should be empty.");

		log.info("End {}.", method);
	}

	@Test
	void when_delete_user_fails_should_throw_exception() {
		String method = "when_delete_user_fails_should_throw_exception";
		log.info("Start {}.", method);

		when(userRepositoryAdapter.deleteUser(user.getId())).thenReturn(false);

		IllegalStateException exception = assertThrows(IllegalStateException.class,
				() -> deleteUserUseCase.deleteUser(user.getId()));

		assertEquals("Failed to delete user with ID: " + user.getId(), exception.getMessage());

		log.info("End {}.", method);
	}
}
