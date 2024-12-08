package com.javiergutierrez.inven_track.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column()
	@NotNull
	private Boolean enabled;

	@Column(unique=true)
	@NotBlank
	private String name;

	@Column()
	@NotBlank
	private String password;

	@Column()
	private String email;

	@Column()
	private String rol;

}
