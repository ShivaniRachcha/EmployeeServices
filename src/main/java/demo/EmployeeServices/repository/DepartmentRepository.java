package demo.EmployeeServices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.EmployeeServices.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Optional<Department> findByDeptCode(String deptCode);

}
