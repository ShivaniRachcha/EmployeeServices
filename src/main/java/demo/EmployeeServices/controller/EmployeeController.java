package demo.EmployeeServices.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.EmployeeServices.entity.Employee;
import demo.EmployeeServices.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String service(){
		return "Hello Employee Service";
	}

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee toSave) {
		return employeeService.saveEmployee(toSave);
	}

	@GetMapping("/{code}")
	public Employee getEmployeeByCode(@PathVariable String code) {
		return employeeService.getEmployeeByCode(code);
	}

	@GetMapping("/all")
	public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		return employeeService.getAllEmployees(page, size);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody Employee toUpdate) {
		return employeeService.updateEmployee(toUpdate);
	}

	@DeleteMapping("/{code}")
	public String deleteEmployee(@PathVariable String code) {
		return employeeService.deleteEmployee(code);
	}

	@PatchMapping
	public Employee updateEmployeeEmail(@RequestBody Employee toUpdate) {
		return employeeService.updateEmployeeEmail(toUpdate);
	}

	@GetMapping("/test")
	@Async
	public void test() {
		int i = 0;
		while (i != 100000000) {
			i=add(i);
			print(i);
		}
	}
	
	private void print(int i) {
		log.info("count={}", i);

	}
	private int add(int i) {
		log.info("adding");
		i++;
		return i;
	}
}
