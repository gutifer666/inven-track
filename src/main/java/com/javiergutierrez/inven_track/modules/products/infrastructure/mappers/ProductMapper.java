package com.javiergutierrez.inven_track.modules.products.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.products.domain.Product;
import com.javiergutierrez.inven_track.modules.products.infrastructure.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<ProductEntity, Product> {
}
