package me.tokanes.recipeshw2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	@GetMapping
	public String index(){
		return "приложение запущено";
	}

	@GetMapping("/info")
	public String about(){
		return "Токан Е.С.";
	}
}
