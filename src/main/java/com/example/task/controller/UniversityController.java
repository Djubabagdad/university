package com.example.task.controller;

import com.example.task.controller.response.ResponseTemplates;
import com.example.task.entity.Lector;
import com.example.task.entity.Statistic;
import com.example.task.service.DepartmentService;
import com.example.task.service.LectorService;
import com.example.task.service.StatisticService;
import com.example.task.service.impl.LectorServiceImpl;
import com.example.task.validation.Validator;
import exception.RequestException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;

@RestController
@RequestMapping("/university/")
public class UniversityController {

    private final Logger logger = LoggerFactory.getLogger(LectorServiceImpl.class);

    private final Validator validator;
    private final LectorService lectorService;
    private final StatisticService statisticService;
    private final DepartmentService departmentService;

    public UniversityController(Validator validator, LectorService lectorService,
                                StatisticService statisticService,
                                DepartmentService departmentService) {
        this.validator = validator;
        this.lectorService = lectorService;
        this.statisticService = statisticService;
        this.departmentService = departmentService;
    }

    @GetMapping("/head/{department_name}")
    public ResponseEntity<String> headOfDepartment(
            @PathVariable(name = "department_name") String departmentName) {
        try {
            validator.validateDepartment(departmentName);
            Lector lector = lectorService.findBy(departmentName);
            String response;

            if (lector.getName() != null) {
                String template = ResponseTemplates.HEAD_OF_DEPARTMENT_TEMPLATE;
                response = String.format(template, departmentName, lector.getName());
            } else {
                String template = ResponseTemplates.NO_ONE_HEAD_OF_DEPARTMENT_TEMPLATE;
                response = String.format(template, departmentName);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException | RequestException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/statistic/{department_name}")
    public ResponseEntity<String> getStatistic(
            @PathVariable(name = "department_name") String departmentName) {
        try {
            validator.validateDepartment(departmentName);
            Statistic statistic = statisticService.getBy(departmentName);

            String template = ResponseTemplates.SHOW_STATISTICS;
            String response = String.format(template,
                    statistic.getAssistantCount(),
                    statistic.getAssociateProfessorsCount(),
                    statistic.getProfessorsCount());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException | RequestException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/salary/average/{department_name}")
    public ResponseEntity<String> getAverageSalary(
            @PathVariable(name = "department_name") String departmentName) {
        try {
            validator.validateDepartment(departmentName);
            String salary = lectorService.countAverageSalaryBy(departmentName)
                    .setScale(2, RoundingMode.DOWN)
                    .toString();
            String template = ResponseTemplates.SHOW_AVERAGE_SALARY;
            String response = String.format(template, departmentName, salary);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NotFoundException | RequestException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/count/lectors/{department_name}")
    public ResponseEntity<String> countOfLectors(
            @PathVariable(name = "department_name") String departmentName) {

        try {
            validator.validateDepartment(departmentName);
            Integer count = lectorService.countLectorsBy(departmentName);
            return new ResponseEntity<>(count.toString(), HttpStatus.OK);
        } catch (NotFoundException | RequestException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{temp}")
    public ResponseEntity<String> search(
            @PathVariable(name = "temp") String temp) {
        String lectorsInfo = lectorService.search(temp);
        String departmentInfo = departmentService.search(temp);

        if (departmentInfo.length() < 1) {
            lectorsInfo = lectorsInfo.substring(0, lectorsInfo.length() - 2);
        } else {
            lectorsInfo = lectorsInfo.concat(departmentInfo);
        }

        return new ResponseEntity<>(lectorsInfo, HttpStatus.OK);
    }
}
