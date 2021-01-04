package com.example.task.service;

import com.example.task.entity.Department;

public interface DepartmentService {

    Department findBy(String departmentName);

    String search(String temp);
}
