package com.javiergutierrez.inven_track.modules.category.infrastructure.mappers;

import com.javiergutierrez.inven_track.common.GenericMapper;
import com.javiergutierrez.inven_track.modules.category.domain.Category;
import com.javiergutierrez.inven_track.modules.category.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<CategoryEntity, Category> {
}
