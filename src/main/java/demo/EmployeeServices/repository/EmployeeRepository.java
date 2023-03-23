package demo.EmployeeServices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.EmployeeServices.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmpCode(String code);

}
