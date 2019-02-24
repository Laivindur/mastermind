package com.cyeste.games.mastermind.adapters.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("homeController")
@RequestMapping(value = "/", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class HomeController {

		
	@Autowired
	public HomeController() {
		
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<ResourceSupport> wellcome() {
		throw new UnsupportedOperationException();
	}
}
