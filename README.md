# 🎁 Gift
이 프로젝트는 e-commerce 도메인의 MSA 환경을 고려한 선물하기 서비스 프로젝트 입니다. <br>
[ 주문 프로젝트 ](https://github.com/pie2457/order)

## 기술 스택

### Infra
![AWS EC2](https://img.shields.io/badge/amazonec2-FF9900?style=flat&logo=amazonec2&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-flat&logo=githubactions&logoColor=white)
![AWSCodeDeploy](https://img.shields.io/badge/CodeDeploy-%23009639.svg?style=for-the-flat&logo=amazoncodedeploy&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-%2496ED.svg?style=for-the-flat&logo=docker&logoColor=white)

### Backend
![Java](https://img.shields.io/badge/-Java-FF7800?style=flat&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-flat&logo=spring&logoColor=white)
![SpringBoot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white)
![SpringDataJPA](https://img.shields.io/badge/SpringDataJpa-236DB33F?style=flat&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white)
![AmazonSQS](https://img.shields.io/badge/AmazonSQS-FF4F8B?style=flat&logo=auth0&logoColor=white)

## 배포 프로세스
<img width="760" alt="스크린샷 2024-07-17 오후 4 30 25" src="https://github.com/user-attachments/assets/8126daa4-2dd6-4788-b716-31ff7423baf6">

## SQS를 이용한 메세지 기반 비동기 통신
<img width="694" alt="스크린샷 2024-07-17 오후 5 14 00" src="https://github.com/user-attachments/assets/8a679a34-b410-4d6f-93e5-5afdbe7fe911">

## 선물 결제 flow
<img width="571" alt="스크린샷 2024-07-17 오후 5 09 27" src="https://github.com/user-attachments/assets/45b68b37-0ee8-4e17-8359-9ec7fa8d9bc1">

## 클래스별 역할
|변수명|설명|
|:---:|:---:|
|XxxCommand|Service 메서드의 처리와 조회를 위한 파라미터|
|XxxInfo|리턴객체 : DB에서 조회하여 가져온 Entity를 그대로 리턴하지 않기 위함|
|XxxStore|도메인의 저장을 담당|
|XxxReader|도메인을 읽어오는 담당|
|XxxFacade|비즈니스 결정을 내리지 않고 수행할 작업을 정의함|
|XxxFactory|객체를 생성하는 일이 복잡해지거나 내부 구조를 너무 많이 드러내는 경우 사용|
