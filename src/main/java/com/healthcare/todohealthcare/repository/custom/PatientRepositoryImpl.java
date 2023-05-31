package com.healthcare.todohealthcare.repository.custom;

import com.healthcare.todohealthcare.dto.search.PatientSearchCondition;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.healthcare.todohealthcare.entitiy.QPatient.patient;
import static org.springframework.util.StringUtils.hasText;

public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PatientRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Patient> searchPatient(PatientSearchCondition condition, Pageable pageable) {
        QueryResults<Patient> results = queryFactory
                .select(patient)
                .from(patient)
                .where(
                        nameEq(condition.getName()),
                        registrationNoEq(condition.getRegistrationNo()),
                        birthEq(condition.getBirth())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Patient> content = results.getResults();
        long totalCount = results.getTotal();

        return new PageImpl<>(content, pageable, totalCount);
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? patient.name.eq(name) : null;
    }

    private BooleanExpression registrationNoEq(String registrationNo) {
        return hasText(registrationNo) ? patient.registrationNo.eq(registrationNo) : null;
    }

    private BooleanExpression birthEq(String birth) {
        return hasText(birth) ? patient.birth.eq(birth) : null;
    }
}
