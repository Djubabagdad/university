package com.example.task.service.impl;

import com.example.task.entity.Department;
import com.example.task.repository.DepartmentRepository;
import com.example.task.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findBy(String departmentName) {
        Department department = departmentRepository.findBy(departmentName);
        if (department != null) {
            return department;
        } else {
            return new Department();
        }
    }

    @Override
    public String search(String temp) {
        return departmentRepository.search(temp)
                .stream()
                .map(Department::getName)
                .collect(Collectors.joining(", "));
    }
}
