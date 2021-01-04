package com.example.task.repository;

public interface NativeQuery {

    String FIND_HEAD_OF_DEPARTMENT_BY_DEPARTMENT =
            "SELECT * FROM lectors l " +
                    " LEFT JOIN departments d ON d.department_head_id = l.id " +
                    " WHERE d.department_name = ?1";

    String COUNT_EMPLOYEES_FOR_STATISTIC_BY_DEPARTMENT =
            "SELECT " +
                    " COALESCE(COUNT(CASE WHEN l.degree = 'ASSISTANT' THEN 1 END), 0) AS assistant_count, " +
                    " COALESCE(COUNT(CASE WHEN l.degree = 'ASSOCIATE_PROFESSOR' THEN 1 END), 0) AS associate_professor_count, " +
                    " COALESCE(COUNT(CASE WHEN l.degree = 'PROFESSOR' THEN 1 END), 0) AS professor_count " +
                    " FROM departments d " +
                    "   LEFT JOIN department_lectors dl ON d.id = dl.department_id " +
                    "   LEFT JOIN lectors l ON dl.lector_id = l.id " +
                    " WHERE d.department_name = :department_name ";

    String COUNT_AVERAGE_SALARY_BY_DEPARTMENT =
            " SELECT AVG(l.salary) " +
                    " FROM departments d" +
                    "    LEFT JOIN department_lectors dl ON d.id = dl.department_id " +
                    "    LEFT JOIN lectors l ON l.id = dl.lector_id  " +
                    " WHERE d.department_name = ?1 ";

    String COUNT_LECTORS_BY_DEPARTMENT =
            " SELECT COUNT(dl.*)" +
                    " FROM departments d " +
                    "   LEFT JOIN department_lectors dl ON dl.department_id = d.id " +
                    " WHERE d.department_name = ?1 ";

    String SEARCH_IN_DEPARTMENTS =
            "SELECT * " +
                    " FROM departments d " +
                    " WHERE LOWER(d.department_name) LIKE LOWER(CONCAT('%',?1,'%'))";

    String SEARCH_IN_LECTORS =
            "SELECT * " +
                    " FROM lectors l " +
                    " WHERE LOWER(CONCAT(l.degree, ' ', l.name, ' ', l.salary)) LIKE LOWER(CONCAT('%',?1,'%'))";

    String FIND_BY_DEPARTMENT_NAME = "SELECT * FROM departments d WHERE d.department_name = ?1 ";

}
