package com.javiergutierrez.inven_track.modules.products.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@EqualsAndHashCode()
public class Product implements Cloneable {

	@Schema(description = "Identificador único del producto.")
	private Long id;

	@NotBlank(message = "El nombre del producto no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del producto no puede tener más de 50 caracteres.")
	@Schema(description = "Nombre del producto.")
	private String name;

	@NotBlank(message = "La descripción del producto no puede estar vacía ni ser nula.")
	@Size(max = 255, message = "La descripción del producto no puede tener más de 255 caracteres.")
	@Schema(description = "Descripción del producto.")
	private String description;

	@NotBlank(message = "La categoría del producto no puede estar vacía ni ser nula.")
	@Size(max = 50, message = "La categoría del producto no puede tener más de 50 caracteres.")
	@Schema(description = "Descripción de la  categoría.")
	private String category;

	@NotNull(message = "El precio del producto no puede ser nulo.")
	@Schema(description = "Precio del producto.")
	private Double price;

	@NotNull(message = "La cantidad del producto no puede ser nula.")
	@Schema(description = "Cantidad del producto.")
	private Integer quantity;

	@Override
	public Product clone() {
		try {
			return (Product) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
