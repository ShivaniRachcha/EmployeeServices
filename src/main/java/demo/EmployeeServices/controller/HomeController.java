package demo.EmployeeServices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/test")
	public String hello() {
		return "HIIIIIIIIIII";
	}
}