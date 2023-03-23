package demo.EmployeeServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.EmployeeServices.entity.Department;
import demo.EmployeeServices.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/department")

public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepo;

	@GetMapping("/all")
	public Page<Department> getAllDepartments(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size) {
		return departmentRepo.findAll(PageRequest.of(page, size));
	}
}
