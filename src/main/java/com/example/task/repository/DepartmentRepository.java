package com.example.task.repository;

import com.example.task.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = NativeQuery.FIND_BY_DEPARTMENT_NAME, nativeQuery = true)
    Department findBy(String departmentName);

    @Query(value = NativeQuery.SEARCH_IN_DEPARTMENTS, nativeQuery = true)
    List<Department> search(String temp);
}
