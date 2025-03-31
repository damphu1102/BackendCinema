package com.example.backendcinema.Specification.ShowTime;

import com.example.backendcinema.entity.ShowTime;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ShowTimeSpecification {
    public static Specification<ShowTime> buildCondition(LocalDate date) {
        return Specification.where(buildWithShowDate(date));
    }

    private static Specification<ShowTime> buildWithShowDate(LocalDate date) {
        if (date != null) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("date"), date);
        } else {
            return null;
        }
    }
}
