package com.javiergutierrez.inven_track.modules.users.infrastructure.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "ROLES")
@Schema(description = "Representa el rol de un usuario.")
@Entity
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único del rol.")
	private Long id;

	@NotBlank(message = "El nombre del rol no puede estar vacío ni ser nulo.")
	@Size(max = 50, message = "El nombre del rol no puede tener más de 50 caracteres.")
	@Column(nullable = false, length = 50)
	@Schema(description = "Nombre del rol.")
	private String name;

}
