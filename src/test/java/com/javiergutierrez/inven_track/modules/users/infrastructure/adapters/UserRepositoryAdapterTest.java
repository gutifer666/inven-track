package com.javiergutierrez.inven_track.modules.users.infrastructure.adapters;

import com.javiergutierrez.inven_track.modules.users.domain.models.Role;
import com.javiergutierrez.inven_track.modules.users.domain.models.User;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.RoleEntity;
import com.javiergutierrez.inven_track.modules.users.infrastructure.entities.UserEntity;
import com.javiergutierrez.inven_track.modules.users.infrastructure.mappers.UserMapper;
import com.javiergutierrez.inven_track.modules.users.infrastructure.mappers.UserMapperImpl;
import com.javiergutierrez.inven_track.modules.users.infrastructure.repositories.IJpaUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserRepositoryAdapterTest {

	@Mock
	private IJpaUserRepository iJpaUserRepository;
	@Mock
	private UserMapper userMapper;
	@InjectMocks
	private UserRepositoryAdapter userRepositoryAdapter;

	private static AutoCloseable closeable;
	private static UserMapper userMapperImpl = new UserMapperImpl();

	private final Role role = Role.builder().id(1L).name("Admin").build();
	private final RoleEntity roleEntity = RoleEntity.builder().id(1L).name("Admin").build();

	private User user;
	private User expectedUser;
	private UserEntity userEntity;
	private UserEntity expectedUserEntity;

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

		userEntity = UserEntity.builder()
				.id(1L)
				.username("jdoe")
				.password("password123")
				.email("admin@gmail.com")
				.role(roleEntity)
				.build();

		expectedUser = user.clone();
		expectedUserEntity = userMapperImpl.toEntity(expectedUser);

		log.info("End to beforeEach");

	}

	@AfterEach
	void afterEach() throws Exception {
		closeable.close();
	}

	@Test
	void when_create_user_should_be_a_new_user() {
		String method = "when_create_user_should_be_a_new_user";
		log.info("Start {}", method);

/*		when(userMapper.toEntity(user)).thenReturn(userEntity);
		when(iJpaUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		when(userMapper.toModel(any(UserEntity.class))).thenReturn(expectedUser);

		Optional<User> resultOptional = userRepositoryAdapter.createUser(user);

		verify(userMapper).toEntity(user);
		verify(iJpaUserRepository).save(any(UserEntity.class));
		verify(userMapper).toModel(any(UserEntity.class));

		assertTrue(resultOptional.isPresent(), "Result should be present");*/

		log.info("End {}", method);
	}
}
