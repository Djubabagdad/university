package com.example.task.service.impl;

import com.example.task.entity.Statistic;
import com.example.task.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final EntityManager entityManager;

    public StatisticServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Statistic getBy(String departmentName) {
        return (Statistic) entityManager.createNamedQuery("showStatistics")
                .setParameter("department_name", departmentName)
                .getSingleResult();
    }
}
