package com.javiergutierrez.inven_track.security.controller;

import com.javiergutierrez.inven_track.security.entity.UserEntity;
import com.javiergutierrez.inven_track.security.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;

	@PostMapping
	public ResponseEntity<UserEntity> createMember(@Valid @RequestBody UserEntity systemMember) {
		return ResponseEntity.ok(userService.create(systemMember));

	}
}
