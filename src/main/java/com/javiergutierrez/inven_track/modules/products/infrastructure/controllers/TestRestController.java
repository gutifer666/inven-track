package com.javiergutierrez.inven_track.modules.products.infrastructure.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/test")
@Slf4j
@RestController
public class TestRestController {

	@GetMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void testEndpoint() {
		log.info("Test endpoint");
	}

}
