package com.javiergutierrez.inven_track.modules.products.application.services;

import com.javiergutierrez.inven_track.modules.products.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@AllArgsConstructor
@Slf4j
@Service
public class ProductService {

	private final Product product = Product.builder()
			.name("Product 1")
			.description("Description of Product 1")
			.category("Category 1")
			.price(10.0)
			.quantity(10)
			.build();

	public Optional<Product> findProductById(Long id) {
		log.info("Call to findProductById with id {}.", id);
		product.setId(id);
		return Optional.of(product);
	}

	public Optional<Product> deleteProduct(Long id) {
		log.info("Call to deleteProduct with id {}.", id);
		product.setId(id);
		return Optional.of(product);
	}
/*	private CreateUserUseCase createUserUseCase;
	private RetrieveUserUseCase retrieveUserUseCase;
	private UpdateUserUseCase updateUserUseCase;
	private DeleteUserUseCase deleteUserUseCase;

	public Optional<User> createUser(User user) {
		log.info("Call to createUser.");
		log.debug(user.toString());
		return createUserUseCase.createUser(user);
	}

	public Optional<User> findUserById(Long id) {
		log.info("Call to findUserById with id {}.", id);
		return retrieveUserUseCase.findUserById(id);
	}

	public Optional<User> updateUser(Long id, User user) {
		log.info("Call to updateUser with id {}.", id);
		log.debug(user.toString());
		return updateUserUseCase.updateUser(id, user);
	}

	public Optional<User> deleteUser(Long id) {
		log.info("Call to deleteUser with id {}.", id);
		return deleteUserUseCase.deleteUser(id);
	}

	public Optional<List<User>> findAllUsers() {
		log.info("Call to findAllUsers.");
		return retrieveUserUseCase.findAllUsers();
	}*/

}
