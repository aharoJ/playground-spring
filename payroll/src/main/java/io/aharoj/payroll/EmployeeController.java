package io.aharoj.payroll;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    private final EmployeeModelAssembler assembler;

    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }

    // // OLD CODE
    // @GetMapping("/employees")
    // List<Employee> all() {
    // return repository.findAll();
    // }

    // // OLD CODE V2
    // @GetMapping("/employees")
    // CollectionModel<EntityModel<Employee>> all() {
    //
    // List<EntityModel<Employee>> employees = repository.findAll().stream()
    // .map(employee -> EntityModel.of(employee,
    // linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
    // .collect(Collectors.toList());
    //
    // return CollectionModel.of(employees,
    // linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    // }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    // // OLD CODE
    // @GetMapping("/employees/{id}")
    // Employee one(@PathVariable Long id) {
    //
    // return repository.findById(id)
    // .orElseThrow(() -> new EmployeeNotFoundException(id));
    // }

    // // OLD CODE V2
    // @GetMapping("/employees/{id}")
    // EntityModel<Employee> one(@PathVariable Long id) {
    //
    // Employee employee = repository.findById(id) //
    // .orElseThrow(() -> new EmployeeNotFoundException(id));
    //
    // return EntityModel.of(employee, //
    // linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
    // linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    // }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
