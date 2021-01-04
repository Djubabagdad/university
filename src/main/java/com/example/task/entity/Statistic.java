package com.example.task.entity;

import com.example.task.repository.NativeQuery;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name = "showStatistics",
        query = NativeQuery.COUNT_EMPLOYEES_FOR_STATISTIC_BY_DEPARTMENT,
        resultSetMapping = "statistic")
@SqlResultSetMapping(name = "statistic", classes = {
        @ConstructorResult(targetClass = Statistic.class,
                columns = {
                        @ColumnResult(name = "assistant_count", type = Long.class),
                        @ColumnResult(name = "associate_professor_count", type = Long.class),
                        @ColumnResult(name = "professor_count", type = Long.class)
                })
})
public class Statistic extends AbstractPersistable<Long> {

    @Column(name = "assistant_count")
    Long assistantCount;

    @Column(name = "associate_professor_count")
    Long associateProfessorsCount;

    @Column(name = "professor_count")
    Long professorsCount;

    public Statistic(Long assistantCount, Long associateProfessorsCount, Long professorsCount) {
        this.assistantCount = assistantCount;
        this.associateProfessorsCount = associateProfessorsCount;
        this.professorsCount = professorsCount;
    }

    public Long getAssistantCount() {
        return assistantCount;
    }

    public void setAssistantCount(Long assistantCount) {
        this.assistantCount = assistantCount;
    }

    public Long getAssociateProfessorsCount() {
        return associateProfessorsCount;
    }

    public void setAssociateProfessorsCount(Long associateProfessorsCount) {
        this.associateProfessorsCount = associateProfessorsCount;
    }

    public Long getProfessorsCount() {
        return professorsCount;
    }

    public void setProfessorsCount(Long professorsCount) {
        this.professorsCount = professorsCount;
    }
}
