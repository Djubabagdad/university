package com.example.task.validation;

import com.example.task.entity.Department;
import com.example.task.service.DepartmentService;
import exception.RequestException;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    private final DepartmentService departmentService;

    public Validator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void validateDepartment(String departmentName) throws NotFoundException, RequestException {
        if (departmentName == null || departmentName.isEmpty()) {
            throw new RequestException("Department name must not be null");
        }

        Department department = departmentService.findBy(departmentName);

        if (department.getName() == null) {
            throw new NotFoundException(String.format("Department with name %s not found", departmentName));
        }
    }
}
