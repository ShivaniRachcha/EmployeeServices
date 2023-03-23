package demo.EmployeeServices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, columnDefinition = "char(3)")
	private String deptCode;

	@Column(unique = true)
	private String name;

	private Long empolyeeCount=0l;

	public Department(String deptCode, String name) {
		this.deptCode = deptCode;
		this.name = name;
	}
}
