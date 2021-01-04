package com.example.task.repository;

import com.example.task.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = NativeQuery.FIND_HEAD_OF_DEPARTMENT_BY_DEPARTMENT,
            nativeQuery = true)
    Lector findBy(String departmentName);

    @Query(value = NativeQuery.COUNT_AVERAGE_SALARY_BY_DEPARTMENT, nativeQuery = true)
    BigDecimal countAverageSalaryBy(String departmentName);

    @Query(value = NativeQuery.COUNT_LECTORS_BY_DEPARTMENT, nativeQuery = true)
    Integer countLectorsBy(String departmentName);

    @Query(value = NativeQuery.SEARCH_IN_LECTORS, nativeQuery = true)
    List<Lector> search(String temp);
}
