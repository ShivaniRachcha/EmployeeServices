package demo.EmployeeServices;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.EmployeeServices.entity.Department;
import demo.EmployeeServices.repository.DepartmentRepository;

@Component
public class MasterTables {

	@Autowired
	private DepartmentRepository departmentRepo;

	@PostConstruct
	private void init() {
		if (departmentRepo.count() == 0) {
			List<Department> list = Arrays.asList(new Department("ITC", "IT and Computers"),
					new Department("SEC", "Security"));
			departmentRepo.saveAll(list);
		}
	}
}
