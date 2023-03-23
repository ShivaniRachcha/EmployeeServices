package demo.EmployeeServices.service;

import org.springframework.data.domain.Page;

import demo.EmployeeServices.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee toSave);

	Employee getEmployeeByCode(String code);

	Page<Employee> getAllEmployees(int page, int size);

	Employee updateEmployee(Employee toUpdate);

	String deleteEmployee(String code);

	Employee updateEmployeeEmail(Employee toUpdate);

}
