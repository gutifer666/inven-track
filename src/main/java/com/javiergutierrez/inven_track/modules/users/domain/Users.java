package com.javiergutierrez.inven_track.modules.users.domain;

import com.javiergutierrez.inven_track.modules.category.domain.Category;
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
public class Users implements Cloneable {

	@Schema(description = "Identificador único del usuario.")
	private Long id;

	@NotBlank(message = "El nombre del usuario no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del usuario no puede tener más de 50 caracteres.")
	@Schema(description = "Nombre del usuario.")
	private String username;

	@NotBlank(message = "El password del usuario no puede estar vacía ni ser nula.")
	@Size(max = 100, message = "El password del usuario no puede tener más de 100 caracteres.")
	@Schema(description = "Password del usuario.")
	private String password;

	@Override
	public Users clone() {
		try {
			return (Users) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
