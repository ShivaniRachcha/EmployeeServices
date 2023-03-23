package demo.EmployeeServices.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import demo.EmployeeServices.entity.Department;
import demo.EmployeeServices.entity.Employee;
import demo.EmployeeServices.exceptions.DataValidationFailedException;
import demo.EmployeeServices.repository.DepartmentRepository;
import demo.EmployeeServices.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Override
	public Employee saveEmployee(Employee toSave) {
		Optional<Department> optionalDept = departmentRepo.findByDeptCode(toSave.getDepartment().getDeptCode());
		if (!optionalDept.isPresent())
			throw new DataValidationFailedException(
					"Department not found for this code: " + toSave.getDepartment().getDeptCode());

		Department department = optionalDept.get();
		toSave.setDepartment(department);
		String code = department.getDeptCode() + toSave.getMobile().substring(6);
		toSave.setEmpCode(code);
		Employee saved = employeeRepo.save(toSave);

		department.setEmpolyeeCount(department.getEmpolyeeCount() + 1);
		departmentRepo.save(department);
		return saved;
	}

	@Override
	public Employee getEmployeeByCode(String code) {
		Optional<Employee> optionalEmployee = employeeRepo.findByEmpCode(code);
		if (!optionalEmployee.isPresent())
			throw new DataValidationFailedException("Employee not found for this code: " + code);
		return optionalEmployee.get();
	}

	@Override
	public Page<Employee> getAllEmployees(int page, int size) {
		return employeeRepo.findAll(PageRequest.of(page, size));
	}

	@Override
	public Employee updateEmployee(Employee toUpdate) {
		Employee dbEmployee = getEmployeeByCode(toUpdate.getEmpCode());
		if (toUpdate.getName() != null && !toUpdate.getName().isBlank() && !toUpdate.getName().isEmpty())
			dbEmployee.setName(toUpdate.getName());
		if (toUpdate.getMobile() != null && !toUpdate.getMobile().isBlank() && !toUpdate.getMobile().isEmpty())
			dbEmployee.setMobile(toUpdate.getMobile());
		if (toUpdate.getEmail() != null && !toUpdate.getEmail().isBlank() && !toUpdate.getEmail().isEmpty())
			dbEmployee.setEmail(toUpdate.getEmail());
		if (toUpdate.getSalary() != null && toUpdate.getSalary() > 0)
			dbEmployee.setSalary(toUpdate.getSalary());
		dbEmployee.setUpdatedAt(LocalDateTime.now());
		return employeeRepo.save(dbEmployee);
	}

	@Override
	public String deleteEmployee(String code) {
		Employee dbEmployee = getEmployeeByCode(code);
		Department department = dbEmployee.getDepartment();
		department.setEmpolyeeCount(department.getEmpolyeeCount() - 1);
		departmentRepo.save(department);
		
		employeeRepo.delete(dbEmployee);
		return "Deleted successfully!";
	}

	@Override
	public Employee updateEmployeeEmail(Employee toUpdate) {
		Employee dbEmployee = getEmployeeByCode(toUpdate.getEmpCode());
		if (toUpdate.getEmail() != null && !toUpdate.getEmail().isBlank() && !toUpdate.getEmail().isEmpty())
			dbEmployee.setEmail(toUpdate.getEmail());
		return employeeRepo.save(dbEmployee);
	}
}
