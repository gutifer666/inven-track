package com.javiergutierrez.inven_track.common;

import java.util.List;

public interface GenericMapper<Entity, Model> {
	Model toModel(Entity entity);
	Entity toEntity(Model model);
	List<Model> toModelList(List<Entity> entities);
	List<Entity> toEntityList(List<Model> models);
}
