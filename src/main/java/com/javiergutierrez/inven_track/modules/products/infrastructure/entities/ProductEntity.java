package com.javiergutierrez.inven_track.modules.products.infrastructure.entities;

import com.javiergutierrez.inven_track.modules.category.infrastructure.entities.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "products")
@Schema(description = "Representa un producto en el sistema.")
@Entity
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único del producto.")
	private Long id;

	@NotBlank(message = "El código del producto no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El código del producto no puede tener más de 50 caracteres.")
	@Column(nullable = false, length = 50)
	@Schema(description = "Código del producto.")
	private String code;

	@NotBlank(message = "El nombre del producto no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del producto no puede tener más de 50 caracteres.")
	@Column(nullable = false, length = 50)
	@Schema(description = "Nombre del producto.")
	private String name;

	@NotBlank(message = "La descripción del producto no puede estar vacía ni ser nula.")
	@Size(max = 255, message = "La descripción del producto no puede tener más de 255 caracteres.")
	@Column(nullable = false, length = 255)
	@Schema(description = "Descripción del producto.")
	private String description;

	@NotNull(message = "La categoría del producto no puede ser nula.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Schema(description = "Categoría a la que pertenece el producto.")
	private CategoryEntity category;

	@NotNull(message = "El precio del producto no puede ser nulo.")
	@Column(nullable = false)
	@Schema(description = "Precio del producto.")
	private Double price;

	@NotNull(message = "La cantidad del producto no puede ser nula.")
	@Column(nullable = false)
	@Schema(description = "Cantidad del producto.")
	private Integer quantity;

}
