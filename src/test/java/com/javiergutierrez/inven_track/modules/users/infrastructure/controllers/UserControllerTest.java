package com.javiergutierrez.inven_track.modules.users.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javiergutierrez.inven_track.modules.users.application.services.UserService;
import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@MockitoBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();
	static AutoCloseable closeable;

	private final Role role = Role.builder().id(1L).name("Admin").build();
	private User user;
	private User expectedUser;

	@BeforeEach
	void beforeEach() {
		log.info("Start to prepare beforeEach");

		closeable = MockitoAnnotations.openMocks(this);

		user = User.builder()
				.id(1L)
				.username("jdoe")
				.password("password123")
				.email("admin@gmail.com")
				.role(role)
				.build();

		expectedUser = user.clone();

		log.info("End of beforeEach");
	}

	@AfterAll
	static void afterAll() throws Exception {
		log.info("Start to AfterAll");
		closeable.close();
		log.info("End to AfterAll");
		log.info("UserServiceTest: Finishing tests");

	}

	// Happy path tests

	@Test
	@WithMockUser(username = "jdoe", password = "password123")
	void when_create_user_should_be_a_new_user() throws Exception {
		String method = "when_create_user_should_be_a_new_user";
		log.info("Start {}", method);

		when(userService.createUser(user)).thenReturn(Optional.of(user));

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(expectedUser.getId()))
				.andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$.password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$.role.id").value(expectedUser.getRole().getId()));

		log.info("End {}", method);

	}

	@Test
	void when_find_all_users_should_be_a_list_of_users() throws Exception {
		String method = "when_find_all_users_should_be_a_list_of_users";
		log.info("Start {}", method);

		List<User> userList = List.of(user);

		when(userService.findAllUsers()).thenReturn(Optional.of(userList));


		String userListJson = objectMapper.writeValueAsString(userList);


		mockMvc.perform(get("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userListJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(expectedUser.getId()))
				.andExpect(jsonPath("$[0].username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$[0].password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$[0].email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$[0].role.id").value(expectedUser.getRole().getId()));

		verify(userService).findAllUsers();

		log.info("End {}", method);
	}

	@Test
	void when_find_user_by_id_should_be_a_user() throws Exception {
		String method = "when_find_user_by_id_should_be_a_user";
		log.info("Start {}", method);

		when(userService.findUserById(user.getId())).thenReturn(Optional.of(user));

		mockMvc.perform(get("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedUser.getId()))
				.andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$.password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$.role.id").value(expectedUser.getRole().getId()));

		verify(userService).findUserById(user.getId());

		log.info("End {}", method);
	}

	@Test
	void when_update_user_should_be_a_updated_user() throws Exception {
		String method = "when_update_user_should_be_a_updated_user";
		log.info("Start {}", method);

		when(userService.updateUser(user.getId(), user)).thenReturn(Optional.of(user));

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(put("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedUser.getId()))
				.andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$.password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$.role.id").value(expectedUser.getRole().getId()));

		verify(userService).updateUser(user.getId(), user);

		log.info("End {}", method);
	}

	@Test
	void when_delete_user_should_be_a_deleted_user() throws Exception {
		String method = "when_delete_user_should_be_a_deleted_user";
		log.info("Start {}", method);

		when(userService.deleteUser(user.getId())).thenReturn(Optional.of(user));

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(delete("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedUser.getId()))
				.andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$.password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$.role.id").value(expectedUser.getRole().getId()));

		verify(userService).deleteUser(user.getId());

		log.info("End {}", method);
	}

	// Exception tests

	@Test
	void when_create_user_should_be_an_exception() throws Exception {
		String method = "when_create_user_should_be_an_exception";
		log.info("Start {}", method);

		when(userService.createUser(any(User.class))).thenReturn(Optional.empty());

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Failed to create user."));

		log.info("End {}", method);
	}

	@Test
	void when_find_user_by_id_should_be_an_exception() throws Exception {
		String method = "when_find_user_by_id_should_be_an_exception";
		log.info("Start {}", method);

		when(userService.findUserById(user.getId())).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Failed to find user with ID: " + user.getId()));

		verify(userService).findUserById(user.getId());

		log.info("End {}", method);
	}

	@Test
	void when_update_user_should_be_an_exception() throws Exception {
		String method = "when_update_user_should_be_an_exception";
		log.info("Start {}", method);

		when(userService.updateUser(user.getId(), user)).thenReturn(Optional.empty());

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(put("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Failed to update user with ID: " + user.getId()));

		verify(userService).updateUser(user.getId(), user);

		log.info("End {}", method);
	}

	@Test
	void when_delete_user_should_be_an_exception() throws Exception {
		String method = "when_delete_user_should_be_an_exception";
		log.info("Start {}", method);

		when(userService.deleteUser(user.getId())).thenReturn(Optional.empty());

		String userJson = objectMapper.writeValueAsString(user);

		mockMvc.perform(delete("/api/users/{id}", user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("Failed to delete user with ID: " + user.getId()));

		verify(userService).deleteUser(user.getId());

		log.info("End {}", method);
	}

	// Validation tests

	@Test
	void when_create_user_with_null_data_should_be_a_validation_error() throws Exception {
		String method = "when_create_user_with_null_data_should_be_a_validation_error";
		log.info("Start {}", method);

		User invalidUser = User.builder()
				.username(null)
				.password(null)
				.role(null)
				.build();

		String userJson = objectMapper.writeValueAsString(invalidUser);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.username").value(containsString("El nombre del usuario no puede estar vacío ni ser nulo.")))
				.andExpect(jsonPath("$.password").value(containsString("La contraseña del usuario no puede estar vacía ni ser nula.")))
				.andExpect(jsonPath("$.role").value(containsString("El rol del usuario no puede ser nulo.")));

		log.info("End {}", method);

	}

	@Test
	void when_create_user_with_empty_data_should_be_a_validation_error() throws Exception {
		String method = "when_create_user_with_empty_data_should_be_a_validation_error";
		log.info("Start {}", method);

		User invalidUser = User.builder()
				.username("")
				.password("")
				.email("")
				.build();

		String userJson = objectMapper.writeValueAsString(invalidUser);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.username").value(containsString("El nombre del usuario no puede estar vacío ni ser nulo.")))
				.andExpect(jsonPath("$.password").value(containsString("La contraseña del usuario no puede estar vacía ni ser nula.")))
				.andExpect(jsonPath("$.email").value(containsString("El email del usuario no es un email válido.")));

		log.info("End {}", method);

	}

	@Test
	void when_create_user_with_too_long_data_should_be_a_validation_error() throws Exception {
		String method = "when_create_user_with_too_long_data_should_be_a_validation_error";
		log.info("Start {}", method);

		User invalidUser = User.builder()
				.username("a".repeat(51))
				.password("a".repeat(101))
				.build();

		String userJson = objectMapper.writeValueAsString(invalidUser);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.username").value(containsString("El nombre del usuario no puede tener más de 50 caracteres.")))
				.andExpect(jsonPath("$.password").value(containsString("La contraseña del usuario no puede tener más de 100 caracteres.")));

		log.info("End {}", method);

	}

	@Test
	void when_create_user_with_max_length_data_should_be_a_new_user() throws Exception {
		String method = "when_create_user_with_max_length_data_should_be_a_new_user";
		log.info("Start {}", method);

		User validUser = User.builder()
				.username("a".repeat(50))
				.password("a".repeat(100))
				.email("admin@gmail.com")
				.role(role)
				.build();

		User expectedUser = validUser.clone();

		when(userService.createUser(validUser)).thenReturn(Optional.of(validUser));

		String userJson = objectMapper.writeValueAsString(validUser);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(expectedUser.getId()))
				.andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
				.andExpect(jsonPath("$.password").value(expectedUser.getPassword()))
				.andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
				.andExpect(jsonPath("$.role.id").value(expectedUser.getRole().getId()));

		log.info("End {}", method);

	}

	@Test
	void when_create_user_with_invalid_email_should_be_a_validation_error() throws Exception {
		String method = "when_create_user_with_invalid_email_should_be_a_validation_error";
		log.info("Start {}", method);

		User invalidUser = User.builder()
				.email("invalidemail")
				.build();

		String userJson = objectMapper.writeValueAsString(invalidUser);

		mockMvc.perform(post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson))
				.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.email").value(containsString("El email del usuario no es un email válido.")));

		log.info("End {}", method);

	}

}
