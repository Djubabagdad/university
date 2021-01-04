package com.example.task.service;

import com.example.task.entity.Lector;

import java.math.BigDecimal;

public interface LectorService {

    Lector findBy(String departmentName);

    BigDecimal countAverageSalaryBy(String departmentName);

    Integer countLectorsBy(String departmentName);

    String search(String temp);
}
