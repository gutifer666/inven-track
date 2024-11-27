package com.javiergutierrez.inven_track.modules.users.application.usecases.user.queries;

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class RetrieveUserUseCaseTest {

	@Mock
	private UserRepositoryAdapter userRepositoryAdapter;
	@InjectMocks
	private RetrieveUserUseCase retrieveUserUseCase;

	private static AutoCloseable closable;
	private Role role;
	private User user;
	private User expectedUser;

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

		expectedUser = user.clone();

		log.info("End to prepare beforeEach");

	}

	@AfterAll
	static void afterAll() throws Exception {
		log.info("Start to AfterAll");
		closable.close();
		log.info("End to AfterAll");
		log.info("RetrieveUserUseCaseTest: End to tests");
	}

	@Test
	void when_find_all_users_should_be_a_list() {
		String method = "when_find_all_users_should_be_a_list";
		log.info("Start {}", method);

		List<User> userList = List.of(user);

		when(userRepositoryAdapter.findAllUsers()).thenReturn(Optional.of(userList));

		Optional<List<User>> resultList = retrieveUserUseCase.findAllUsers();

		verify(userRepositoryAdapter).findAllUsers();
		assertTrue(resultList.isPresent(), "Result should be present");
		assertEquals(1, resultList.get().size(), "Should return one user");
		assertUser(resultList.get().get(0));

		log.info("End {}.", method);
	}

	@Test
	void when_find_user_by_id_should_return_user() {
		String method = "when_find_user_by_id_should_return_user";
		log.info("Start {}.", method);

		when(userRepositoryAdapter.findUserById(1L)).thenReturn(Optional.of(user));

		Optional<User> resultOptional = retrieveUserUseCase.findUserById(1L);

		verify(userRepositoryAdapter).findUserById(1L);
		assertTrue(resultOptional.isPresent(), "Result should be present");
		assertUser(resultOptional.get());

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
