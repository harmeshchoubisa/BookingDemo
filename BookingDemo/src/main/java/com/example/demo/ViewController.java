package com.example.demo;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("datetime", new Date());
		model.addAttribute("username", "Harmesh Choubisa");
		model.addAttribute("mode", "production");
		return "index";
	}
}
