# todo-healthcare
---

## 개발 환경
- JAVA 17
- Spring Boot 2.7.12
- Spring Data JPA / QueryDSL
- DB : H2 Database
- IDE : IntelliJ
---

## 주요 기능

<details>
<summary>환자</summary>

### 환자 도메인
* 등록 : 병원에 소속된 환자를 등록합니다.
* 수정 : 환자정보를 수정합니다.
* 삭제 : 환자정보가 삭제 처리되어 삭제일자가 update 됩니다.
* 조회 : 소속 병원, 방문 기록을 포함한 환자의 모든 정보를 조회합니다.
* 목록조회 : 검색 조건과 일치하는 전체 환자 목록을 조회합니다.
</details>

<details>
<summary>방문</summary>

### 방문 도메인
* 등록: 환자의 방문 정보를 등록하고 환자의 최근 방문일자를 수정합니다.
* 수정: 환자의 방문 정보를 수정합니다.
* 삭제: 환자의 밤문 정보가 삭제 처리되어 삭제일자가 update 됩니다.
* 조회: 소속 병원, 환자 정보를 포함한 환자의 방문 정보를 조회합니다.
---
</details>



## 확인 사항
1. 서버 실행 시 사전 데이터가 생성될 수 있도록 import.sql 스크립트를 구축해두었습니다.
2. H2 데이터베이스 주소 : http://localhost:8080/h2-console/
---

## API Document
**[공통 Response]**  


**[환자]**
<details>
<summary>환자 등록</summary>

**환자 등록**
---
* **HTTP Method :**
  `POST`
- **URL :**  
/api/patients

* **Request :**  
  * **Body** :  
  `name = [String] - 환자명`  
  `gender = [String] - 성별`  
  `birth = [String] - 생년월일`  
  `phone = [String] - 휴대번호`  
  `hospitalId = [Long] - 병원ID`  


* **Response :**  
  `id = [Long] - 환자ID`  
  `createdDate = [LocalDateTime] - 생성일시`


* **Success Request:**
```
{
    "name": "환자A4",
    "gender": "F",
    "birth": "1990-01-01",
    "phone": "010-1234-5678",
    "hospitalId" : 2
}
```

* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:39:56.2844502",
    "status": 200,
    "code": "OK",
    "data": {
        "id": 5,
        "createdDate": "2023-06-01T18:39:56.2028937"
    }
}
```
---
</details>

<details>
<summary>환자 수정</summary>

**환자 수정**
---
* **HTTP Method :**
  `PUT`
- **URL :**  
  /api/patients/{id}

* **Request :**  
  * **쿼리 파라미터 :**  
  `id = [Long] - 환자ID`  
  
  * **Body** :  
  `name = [String] - 환자명`  
  `gender = [String] - 성별`  
  `birth = [String] - 생년월일`  
  `phone = [String] - 휴대번호`  
  `hospitalId = [Long] - 병원ID`


* **Response :**  
  `name = [String] - 환자명`  
  `gender = [String] - 성별`  
  `birth = [String] - 생년월일`  
  `phone = [String] - 휴대번호`  
  `hospitalId = [Long] - 병원ID`


* **Success Request:**
```
{
    "name": "환자명수정",
    "gender": "F",
    "birth": "1990-01-02",
    "phone": "010-1234-5678",
    "hospitalId" : 1
}
```


* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:44:31.6227232",
    "status": 200,
    "code": "OK",
    "data": {
        "name": "환자명수정",
        "gender": "F",
        "birth": "1990-01-02",
        "phone": "010-1234-5678",
        "hospitalId": 1
    }
}
```
---
</details>

<details>
<summary>환자 삭제</summary>

**환자 삭제**
---
* **HTTP Method :**
  `DELETE`
- **URL :**  
  /api/patients/{id}
* **Request :**
  * **쿼리 파라미터 :**  
    `id = [Long] - 환자ID`


* **Response :**  
  `id = [Long] - 환자ID`  
  `deletedDate = [LocalDateTime] - 삭제일시`

  
* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:26:22.0429129",
    "status": 200,
    "code": "OK",
    "data": {
        "id": 2,
        "deletedDate": "2023-06-01T18:26:22.034911"
    }
}
```
---
</details>

<details>
<summary>환자 조회</summary>

**환자 조회**
---
* **HTTP Method :**
  `GET`
- **URL :**  
  /api/patients/{id}

* **Request :**
  * **쿼리 파라미터 :**  
    `id = [Long] - 환자ID`

* **Response :**  
  `id = [Long] - 환자ID`  
  `name = [String] - 환자명`  
  `registrationNo = [String] - 환자등록번호`    
  `gender = [String] - 성별`  
  `birth = [String] - 생일`  
  `phone = [String] - 휴대번호`  
  `lastVisitDate = [LocalDateTime] - 최근방문일자`  
  `hospital = [hospital] - 소속 병원 정보`  
  `visits = [visits] - 환자 방문 정보`  
 

  
* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:56:45.1313069",
    "status": 200,
    "code": "OK",
    "data": {
        "id": 1,
        "name": "환자A1",
        "registrationNo": "2023010001",
        "gender": "M",
        "birth": "1990-01-01",
        "phone": "010-1111-1111",
        "lastVisitDate": "2023-06-01T18:56:34.107429",
        "hospital": {
            "id": 1,
            "name": "A병원",
            "careCenterNo": "01",
            "director": "A병원장"
        },
        "visits": [
            {
                "visitStateCode": "1",
                "departmentCode": "01",
                "typeCode": "D",
                "receptionDate": "2023-06-01T18:56:34.103413"
            },
            {
                "visitStateCode": "3",
                "departmentCode": "01",
                "typeCode": "T",
                "receptionDate": "2023-06-01T18:56:34.110429"
            }
        ]
    }
}
```
---
</details>

<details>
<summary>환자 목록 조회</summary>

**환자 목록 조회**
---
* **HTTP Method :**
  `GET`
- **URL :**  
  /api/patients?pageNo={pageNo}&pageSize={pageSize}
* **Request :**
  * **쿼리 파라미터 :**  
    `pageNo = [Integer] - 1부터 시작, 페이지 번호`
    `pageSize = [Integer] - 한 번에 조회하는 최대 항목 수`


* **Response :**  
  `pageInfo = [pageInfo] - 페이지정보`  
  `patients = [patients] - 환자정보(환자 조회의 정보와 동일)`


* **Success Response:**
```
{
    "timestamp": "2023-06-01T19:19:34.2008409",
    "status": 200,
    "code": "OK",
    "data": {
        "pageInfo": {
            "pageNo": 1,
            "pageSize": 3,
            "totalCount": 4
        },
        "patients": [
            {
                "id": 1,
                "name": "환자A1",
                "registrationNo": "2023010001",
                "gender": "M",
                "birth": "1990-01-01",
                "phone": "010-1111-1111",
                "lastVisitDate": "2023-06-01T19:19:22.561238"
            },
            {
                "id": 2,
                "name": "환자A2",
                "registrationNo": "2023010002",
                "gender": "F",
                "birth": "1990-02-02",
                "phone": "010-2222-2222",
                "lastVisitDate": "2023-06-01T19:19:22.563232"
            },
            {
                "id": 3,
                "name": "환자B1",
                "registrationNo": "2023020001",
                "gender": "F",
                "birth": "1990-03-03",
                "phone": "010-3333-3333",
                "lastVisitDate": "2023-06-01T19:19:22.565416"
            }
        ]
    }
}
```
---
</details>



**[방문정보]**
<details>
<summary>방문정보 등록</summary>

**방문정보 등록**
---
* **HTTP Method :**
  `POST`
- **URL :**  
  /api/visits

* **Request :**
  * **Body** :  
    `visitStateCode = [String] - 방문상태코드`  
    `departmentCode = [String] - 진료과목코드`  
    `typeCode = [String] - 진료유형코드`  
    `hospitalId = [Long] - 병원ID`  
    `patientId = [Long] - 환자ID`  


* **Response :**  
  `id = [Long] - 방문정보ID`  
  `createdDate = [LocalDateTime] - 생성일시`


* **Success Request:**
```
{
    "visitStateCode" : "2",
    "departmentCode" : "01",
    "typeCode" : "D",
    "hospitalId" : 1,
    "patientId" : 1
}
```

* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:39:56.2844502",
    "status": 200,
    "code": "OK",
    "data": {
        "id": 5,
        "createdDate": "2023-06-01T18:39:56.2028937"
    }
}
```
---
</details>

<details>
<summary>방문정보 수정</summary>

**방문정보 수정**
---
* **HTTP Method :**
  `PUT`
- **URL :**  
  /api/visits/{id}

* **Request :**
  * **쿼리 파라미터 :**  
    `id = [Long] - 방문정보ID`

  * **Body** :  
    `name = [String] - 환자명`  
    `gender = [String] - 성별`  
    `birth = [String] - 생년월일`  
    `phone = [String] - 휴대번호`  
    `hospitalId = [Long] - 병원ID`


* **Response :**  
  `visitStateCode = [String] - 방문상태코드`  
  `departmentCode = [String] - 진료과목코드`  
  `typeCode = [String] - 진료유형코드`   
  `hospitalId = [Long] - 병원ID`  
  `patientId = [Long] - 환자ID`


* **Success Request:**
```
{
    "visitStateCode" : "2",
    "departmentCode" : "02",
    "typeCode" : "T",
    "hospitalId" : 2,
    "patientId" : 1
}
```


* **Success Response:**
```
{
    "timestamp": "2023-06-01T20:05:28.6372255",
    "status": 200,
    "code": "OK",
    "data": {
        "visitStateCode": "2",
        "departmentCode": "02",
        "typeCode": "T",
        "hospitalId": 2,
        "patientId": 1
    }
}
```
---
</details>

<details>
<summary>방문정보 삭제</summary>

**방문정보 삭제**
---
* **HTTP Method :**
  `DELETE`
- **URL :**  
  /api/visits/{id}
* **Request :**
  * **쿼리 파라미터 :**  
    `id = [Long] - 방문정보ID`


* **Response :**  
  `id = [Long] - 방문정보ID`  
  `deletedDate = [LocalDateTime] - 삭제일시`


* **Success Response:**
```
{
    "timestamp": "2023-06-01T18:26:22.0429129",
    "status": 200,
    "code": "OK",
    "data": {
        "id": 2,
        "deletedDate": "2023-06-01T18:26:22.034911"
    }
}
```
---
</details>

<details>
<summary>방문정보 조회</summary>

**방문정보 조회**
---
* **HTTP Method :**
  `GET`
- **URL :**  
  /api/visits/{id}

* **Request :**
  * **쿼리 파라미터 :**  
    `id = [Long] - 방문정보ID`

* **Response :**  
  `visitStateCode = [String] - 방문상태코드`  
  `departmentCode = [String] - 진료과목코드`  
  `typeCode = [String] - 진료유형코드`   
  `hospital = [hospital] - 소속 병원 정보`
* `patient = [patient] - 환자 정보`


* **Success Response:**
```
{
    "timestamp": "2023-06-01T20:10:55.4686635",
    "status": 200,
    "code": "OK",
    "data": {
        "visitStateCode": "1",
        "departmentCode": "01",
        "typeCode": "D",
        "hospital": {
            "id": 1,
            "name": "A병원",
            "careCenterNo": "01",
            "director": "A병원장"
        },
        "patient": {
            "name": "환자A1",
            "gender": "M",
            "birth": "1990-01-01",
            "phone": "010-1111-1111"
        }
    }
}
```
---
</details>