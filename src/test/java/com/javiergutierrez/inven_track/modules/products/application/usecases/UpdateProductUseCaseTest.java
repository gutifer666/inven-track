package com.javiergutierrez.inven_track.modules.products.application.usecases;

import com.javiergutierrez.inven_track.modules.category.domain.Category;
import com.javiergutierrez.inven_track.modules.products.domain.Product;
import com.javiergutierrez.inven_track.modules.products.infrastructure.adapters.ProductRepositoryAdapter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UpdateProductUseCaseTest {

	@Mock
	private ProductRepositoryAdapter productRepositoryAdapter;
	@InjectMocks
	private UpdateProductUseCase updateProductUseCase;

	private static AutoCloseable closable;
	private Product product;
	private Product expectedProduct;

	@BeforeEach
	void beforeEach() {
		log.info("Start to prepare beforeEach");

		closable = MockitoAnnotations.openMocks(this);

		Category category = Category.builder()
				.id(1L)
				.name("General")
				.description("Productos de uso general")
				.build();

		product = Product.builder()
				.id(1L)
				.code("GEN001")
				.name("Bicicleta")
				.description("Bicicleta BH de montaña")
				.category(category)
				.price(500.00)
				.quantity(1)
				.build();

		expectedProduct = product.clone();
	}

	@AfterAll
	static void afterAll() throws Exception {
		log.info("End to tests");
		closable.close();
		log.info("End to AfterAll");
		log.info("\tIUpdateProductUseCaseImplTest: Finishing tests");
	}

	@Test
	void when_update_product_should_be_an_updated_product() {
		String method = "when_update_product_should_be_an_updated_product";
		log.info("Start {}.", method);

		when(productRepositoryAdapter.findProductById(1L)).thenReturn(Optional.of(product));
		when(productRepositoryAdapter.updateProduct(product)).thenReturn(Optional.of(product));

		Optional<Product> resultOptional = updateProductUseCase.updateProduct(1L, product);

		verify(productRepositoryAdapter).findProductById(1L);
		verify(productRepositoryAdapter).updateProduct(product);

		assertTrue(resultOptional.isPresent(), "Result should be present.");
		assertProduct(resultOptional.get());
	}

	@Test
	void when_update_product_should_be_an_exception_when_product_not_found() {
		String method = "when_update_product_should_be_an_exception_when_product_not_found";
		log.info("Start {}.", method);

		when(productRepositoryAdapter.findProductById(1L)).thenReturn(Optional.empty());

		IllegalStateException thrownException = assertThrows(IllegalStateException.class,
				() -> updateProductUseCase.updateProduct(1L, product),
				"Expected updateUser() to throw, but it didn't");

		verify(productRepositoryAdapter).findProductById(1L);

		assertTrue(thrownException.getMessage().contains("Failed to get product with ID: 1"));

		log.info("End {}.", method);
	}

	private void assertProduct(Product product) {

		assertNotNull(product, "Result should not be null.");
		assertEquals(expectedProduct.getId(), product.getId(), "Id should be the same.");
		assertEquals(expectedProduct.getCode(), product.getCode(), "Code should be the same.");
		assertEquals(expectedProduct.getName(), product.getName(), "Name should be the same.");
		assertEquals(expectedProduct.getDescription(), product.getDescription(), "Description should be the same.");
		assertEquals(expectedProduct.getCategory(), product.getCategory(), "Category should be the same.");
		assertEquals(expectedProduct.getPrice(), product.getPrice(), "Price should be the same.");
		assertEquals(expectedProduct.getQuantity(), product.getQuantity(), "Quantity should be the same.");
	}
}
