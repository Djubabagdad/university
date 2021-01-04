package com.example.task.service.impl;

import com.example.task.entity.Lector;
import com.example.task.repository.LectorRepository;
import com.example.task.service.LectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LectorServiceImpl implements LectorService {

    private final Logger logger = LoggerFactory.getLogger(LectorServiceImpl.class);

    private final LectorRepository lectorRepository;

    public LectorServiceImpl(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Override
    public Lector findBy(String departmentName) {
        Lector lector = lectorRepository.findBy(departmentName);

        if (lector != null) {
            return lector;
        } else {
            logger.info("Lector not found");
            return new Lector();
        }
    }

    @Override
    public BigDecimal countAverageSalaryBy(String departmentName) {
        return lectorRepository.countAverageSalaryBy(departmentName);
    }

    @Override
    public Integer countLectorsBy(String departmentName) {
        return lectorRepository.countLectorsBy(departmentName);
    }

    @Override
    public String search(String temp) {
        List<Lector> lectors = lectorRepository.search(temp);

        if (lectors.size() > 0) {
            StringBuilder response = new StringBuilder();

            lectors.forEach(lector -> {
                String name = lector.getName().toLowerCase();
                String degree = lector.getDegree().getName().toLowerCase();
                String salary = lector.getSalary().toString();

                if (name.contains(temp)) {
                    response.append(lector.getName())
                            .append(", ");

                } else if (degree.contains(temp)) {
                    response.append(lector.getDegree().getName())
                            .append(", ");

                } else if (salary.contains(temp)) {
                    response.append(lector.getSalary())
                            .append(", ");
                }

            });
            return response.toString();
        } else {
            return "";
        }
    }
}
