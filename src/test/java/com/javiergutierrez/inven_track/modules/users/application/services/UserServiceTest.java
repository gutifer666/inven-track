package com.javiergutierrez.inven_track.modules.users.application.services;

import com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands.CreateUserUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands.DeleteUserUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.user.commands.UpdateUserUseCase;
import com.javiergutierrez.inven_track.modules.users.application.usecases.user.queries.RetrieveUserUseCase;
import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private CreateUserUseCase createUserUseCase;
	@Mock
	private RetrieveUserUseCase retrieveUserUseCase;
	@Mock
	private UpdateUserUseCase updateUserUseCase;
	@Mock
	private DeleteUserUseCase deleteUserUseCase;
	@InjectMocks
	private UserService userService;

	private static AutoCloseable closable;
	private final Role role = Role.builder().id(1L).name("Admin").build();
	private User user;
	private User expectedUser;

	@BeforeEach
	void beforeEach() {
		log.info("Start to prepare beforeEach");

		closable = MockitoAnnotations.openMocks(this);

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

	@AfterAll
	static void afterAll() throws Exception {
		log.info("Start to AfterAll");
		closable.close();
		log.info("End to AfterAll");
		log.info("UserServiceTest: Finishing tests");

	}

	@Test
	void when_create_user_should_be_a_new_user() {
		String method = "when_create_user_should_be_a_new_user";
		log.info("Start {}", method);

		when(createUserUseCase.createUser(user)).thenReturn(Optional.of(user));

		Optional<User> result = userService.createUser(user);

		verify(createUserUseCase).createUser(user);

		assertTrue(result.isPresent(), "Result should be present.");
		assertUser(result.get());

		log.info("End {}.", method);
	}

	@Test
	void when_find_user_by_id_should_return_user() {
		String method = "when_find_user_by_id_should_return_user";
		log.info("Start {}", method);

		when(retrieveUserUseCase.findUserById(1L)).thenReturn(Optional.of(user));

		Optional<User> result = userService.findUserById(1L);

		verify(retrieveUserUseCase).findUserById(1L);

		assertTrue(result.isPresent(), "Result should be present.");
		assertUser(result.get());

		log.info("End {}.", method);
	}

	@Test
	void when_update_user_should_return_user() {
		String method = "when_update_user_should_return_user";
		log.info("Start {}", method);

		when(updateUserUseCase.updateUser(1L, user)).thenReturn(Optional.of(user));

		Optional<User> result = userService.updateUser(1L, user);

		verify(updateUserUseCase).updateUser(1L, user);

		assertTrue(result.isPresent(), "Result should be present.");
		assertUser(result.get());

		log.info("End {}.", method);
	}

	@Test
	void when_delete_user_should_return_user() {
		String method = "when_delete_user_should_return_user";
		log.info("Start {}", method);

		when(deleteUserUseCase.deleteUser(1L)).thenReturn(Optional.of(user));

		Optional<User> result = userService.deleteUser(1L);

		verify(deleteUserUseCase).deleteUser(1L);

		assertTrue(result.isPresent(), "Result should be present.");
		assertUser(result.get());

		log.info("End {}.", method);
	}

	@Test
	void when_find_all_users_should_return_list_of_users() {
		String method = "when_find_all_users_should_return_list_of_users";
		log.info("Start {}", method);

		when(retrieveUserUseCase.findAllUsers()).thenReturn(Optional.of(List.of(user)));

		Optional<List<User>> result = userService.findAllUsers();

		verify(retrieveUserUseCase).findAllUsers();

		assertTrue(result.isPresent(), "Result should be present.");
		assertNotNull(result.get(), "Result should not be null.");
		assertEquals(1, result.get().size(), "Result should contain a user.");

		User resultUser = result.get().stream()
				.filter(u -> u.getId().equals(1L))
				.findFirst()
				.orElse(null);

		assertNotNull(resultUser, "Result should contain user.");
		assertUser(resultUser);

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
