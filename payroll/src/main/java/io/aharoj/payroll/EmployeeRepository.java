package io.aharoj.payroll;


import org.springframework.data.jpa.repository.JpaRepository;

import io.aharoj.payroll.model.Employee;

interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
