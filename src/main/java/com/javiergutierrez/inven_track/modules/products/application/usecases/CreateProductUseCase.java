package com.javiergutierrez.inven_track.modules.products.application.usecases;

import com.javiergutierrez.inven_track.modules.products.domain.Product;
import com.javiergutierrez.inven_track.modules.products.infrastructure.adapters.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Transactional
@Component
public class CreateProductUseCase {

	private ProductRepositoryAdapter productRepositoryAdapter;

	public Optional<Product> createProduct(Product product) {
		log.info("Call to createProduct {}", product);
		log.debug("Product to create: {}", product);
		Optional<Product> createdProduct = productRepositoryAdapter.createProduct(product);
		if (createdProduct.isEmpty()) {
			throw new IllegalStateException("Failed to create product: " + product);
		}
		return createdProduct;
	}

}
