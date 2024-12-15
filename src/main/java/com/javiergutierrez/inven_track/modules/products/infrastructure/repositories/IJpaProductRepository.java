package com.javiergutierrez.inven_track.modules.products.infrastructure.repositories;

import com.javiergutierrez.inven_track.modules.products.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IJpaProductRepository extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findByCode(String code);

}
