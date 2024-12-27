package com.javiergutierrez.inven_track.modules.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

	@GetMapping("/")
	public String home() {
		return "Welcome to InvenTrack";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}
}
