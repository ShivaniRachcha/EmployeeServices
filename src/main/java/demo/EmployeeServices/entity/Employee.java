package demo.EmployeeServices.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Data;

@Entity
public @Data class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true,columnDefinition = "char(7)")
	private String empCode;

	@Column(unique = true)
	private String email;

	@Column(unique = true, columnDefinition = "char(10)")
	private String mobile;

	private String name;
	private Long salary;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;

	@ManyToOne
	@JsonIncludeProperties(value = {"deptCode","name"})
	private Department department;
}
