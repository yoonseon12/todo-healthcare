package com.healthcare.todohealthcare;

import com.healthcare.todohealthcare.entitiy.Hospital;
import com.healthcare.todohealthcare.entitiy.code.Code;
import com.healthcare.todohealthcare.entitiy.code.CodeGroup;
import com.healthcare.todohealthcare.entitiy.code.CodeId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 스프링 서버 구동 시 데이터를 생성한다.
 */
@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void initDB() {
        initService.initCodeData(); // 코드 테이블 저장
        initService.initHospital(); // 병원정보 저장
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void initCodeData() {
            CodeGroup codeGroup1 = new CodeGroup("성별코드", "성별코드", "성별을 표시");
            CodeGroup codeGroup2 = new CodeGroup("방문상태코드", "방문상태코드", "환자방문의 상태(방문중, 종료, 취소)");
            CodeGroup codeGroup3 = new CodeGroup("진료과목코드", "진료과목코드", "진료과목(내과, 안과)");
            CodeGroup codeGroup4 = new CodeGroup("진료유형코드", "진료유형코드", "진료의 유형(약처방, 검사 등)");
            em.persist(codeGroup1);
            em.persist(codeGroup2);
            em.persist(codeGroup3);
            em.persist(codeGroup4);

            Code code1  = new Code(
                    new CodeId(codeGroup1.getCodeGroup(), "M"), "남");
            Code code2  = new Code(
                    new CodeId(codeGroup1.getCodeGroup(), "F"), "여");
            Code code3  = new Code(
                    new CodeId(codeGroup2.getCodeGroup(), "1"), "방문중");
            Code code4  = new Code(
                    new CodeId(codeGroup2.getCodeGroup(), "2"), "종료");
            Code code5  = new Code(
                    new CodeId(codeGroup2.getCodeGroup(), "3"), "취소");
            Code code6  = new Code(
                    new CodeId(codeGroup3.getCodeGroup(), "01"), "내과");
            Code code7  = new Code(
                    new CodeId(codeGroup3.getCodeGroup(), "02"), "안과");
            Code code8  = new Code(
                    new CodeId(codeGroup4.getCodeGroup(), "D"), "약처방");
            Code code9  = new Code(
                    new CodeId(codeGroup4.getCodeGroup(), "T"), "검사");
            code1.setCodeGroup(codeGroup1);
            code2.setCodeGroup(codeGroup1);
            code3.setCodeGroup(codeGroup2);
            code4.setCodeGroup(codeGroup2);
            code5.setCodeGroup(codeGroup2);
            code6.setCodeGroup(codeGroup3);
            code7.setCodeGroup(codeGroup3);
            code8.setCodeGroup(codeGroup4);
            code9.setCodeGroup(codeGroup4);
            em.persist(code1);
            em.persist(code2);
            em.persist(code3);
            em.persist(code4);
            em.persist(code5);
            em.persist(code6);
            em.persist(code7);
            em.persist(code8);
            em.persist(code9);
        }

        public void initHospital() {
            Hospital hospital1 = Hospital.builder()
                    .name("A병원")
                    .careCenterNo("11111")
                    .director("A병원장")
                    .build();
            Hospital hospital2 = Hospital.builder()
                    .name("B병원")
                    .careCenterNo("22222")
                    .director("B병원장")
                    .build();
            em.persist(hospital1);
            em.persist(hospital2);
        }
    }
}
