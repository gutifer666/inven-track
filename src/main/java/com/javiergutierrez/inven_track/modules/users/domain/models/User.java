package com.javiergutierrez.inven_track.modules.users.domain.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class User implements Cloneable {

	@Schema(description = "Identificador único del usuario.")
	private Long id;

	@NotBlank(message = "El nombre del usuario no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del usuario no puede tener más de 50 caracteres.")
	@Schema(description = "Nombre del usuario.")
	private String username;

	@NotBlank(message = "La contraseña del usuario no puede estar vacía ni ser nula.")
	@Size(max = 100, message = "La contraseña del usuario no puede tener más de 100 caracteres.")
	@Schema(description = "Contraseña del usuario")
	private String password;

	@Size(max = 100, message = "El email del usuario no puede tener más de 100 caracteres.")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
			message = "El email del usuario no es un email válido.")
	@Schema(description = "Email del usuario.")
	private String email;

	@NotNull(message = "El rol del usuario no puede ser nulo.")
	@Schema(description = "Rol del usuario.")
	private Role role;

	@Override
	public User clone() {
		try {
			return (User) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
