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
public class DeleteProductUseCase {

	private ProductRepositoryAdapter productRepositoryAdapter;

	public Optional<Product> deleteProduct(long productId) {
		log.info("Call to deleteProduct {}", productId);
		boolean isDeleted = productRepositoryAdapter.deleteProduct(productId);
		if (!isDeleted) {
			throw new IllegalStateException("Failed to delete product with ID: " + productId);
		}
		return Optional.empty();
	}

}
