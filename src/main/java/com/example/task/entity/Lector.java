package com.example.task.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "degree", nullable = false)
    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "lectors")
    private Set<Department> department = new HashSet<>();

    public Lector() {
    }

    public Lector(Long id, String name, BigDecimal salary, Degree degree) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.degree = degree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return Objects.equals(id, lector.id) &&
                Objects.equals(name, lector.name) &&
                Objects.equals(salary, lector.salary) &&
                degree == lector.degree &&
                Objects.equals(department, lector.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, degree, department);
    }
}
