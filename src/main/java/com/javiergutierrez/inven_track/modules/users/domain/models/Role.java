package com.javiergutierrez.inven_track.modules.users.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class Role implements Cloneable {

	@Schema(description = "Identificador único del rol.")
	private Long id;

	@NotBlank(message = "El nombre del rol no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del rol no puede tener más de 50 caracteres.")
	@Schema(description = "Nombre del rol.")
	private String name;

	@Override
	public Role clone() {
		try {
			return (Role) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
