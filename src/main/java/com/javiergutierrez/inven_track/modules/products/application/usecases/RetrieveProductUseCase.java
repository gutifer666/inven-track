package com.javiergutierrez.inven_track.modules.products.application.usecases;

import com.javiergutierrez.inven_track.modules.products.domain.Product;
import com.javiergutierrez.inven_track.modules.products.infrastructure.adapters.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class RetrieveProductUseCase {

	private ProductRepositoryAdapter productRepositoryAdapter;

	public Optional<List<Product>> findAllProducts() {
		log.info("Call to findAllProducts.");

		Optional<List<Product>> productList = productRepositoryAdapter.findAllProducts();

		log.debug(productList.toString());

		return productList;
	}

	public Optional<Product> findProductById(Long id) {
		log.info("Call to findUserById.");
		return productRepositoryAdapter.findProductById(id);
	}
}
