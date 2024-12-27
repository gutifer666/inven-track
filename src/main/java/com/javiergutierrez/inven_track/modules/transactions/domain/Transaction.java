package com.javiergutierrez.inven_track.modules.transactions.domain;

import com.javiergutierrez.inven_track.modules.products.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@EqualsAndHashCode()
public class Transaction implements Cloneable {

	@Schema(description = "Identificador único de la transacción.")
	private Long id;

	@NotBlank(message = "El nombre del cliente no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del cliente no puede tener más de 50 caracteres.")
	@Column(nullable = false, length = 50)
	@Schema(description = "Nombre del cliente.")
	private String clientName;

	@NotBlank(message = "El código del producto no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El código del producto no puede tener más de 50 caracteres.")
	@Schema(description = "Código del producto.")
	private String productCode;

	@NotBlank(message = "El nombre del producto no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del producto no puede tener más de 50 caracteres.")
	@Schema(description = "Nombre del producto.")
	private String productName;


	@NotNull(message = "El precio de la transacción no puede ser nula.")
	@Schema(description = "Precio de la transacción.")
	private Double transactionPrice;

	@Builder.Default
	@Schema(description = "Fecha de realización de la transacción.")
	private Date createdAt = new Date();

	@Override
	public Transaction clone() {
		try {
			return (Transaction) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	public Transaction(String clientName, Product product)
	{
		this.clientName = clientName;
		this.productCode = product.getCode();
		this.productName = product.getName();
		this.transactionPrice = product.getPrice();
		this.createdAt = new Date();
	}
}
