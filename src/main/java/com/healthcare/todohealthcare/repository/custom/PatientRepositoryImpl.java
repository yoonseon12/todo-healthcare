package com.healthcare.todohealthcare.repository.custom;

import com.healthcare.todohealthcare.dto.search.PatientSearchConditon;
import com.healthcare.todohealthcare.entitiy.Patient;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
    public List<Patient> searchPatient(PatientSearchConditon condition) {
        return queryFactory
                .select(patient)
                .from(patient)
                .where(
                        nameEq(condition.getName()),
                        registrationNoEq(condition.getRegistrationNo()),
                        birthEq(condition.getBirth())
                )
                .fetch();
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
