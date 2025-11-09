# Spring Base
- 스프링 부트 기능 테스트를 위한 프로젝트입니다.
- 브랜치별로 기능을 실험하고 학습합니다.

## ✅ 프로젝트 정보
```
- 프로젝트에 대해 기본적으로 설정된 내용입니다.
- 각 브랜치별로 추가적인 설정이 있을 수 있습니다.
```
- **Java Version**: 25
- **Spring Boot**: 3.5.7
- **Build Tool**: Gradle
- **Database**: PostgreSQL 17

### 기술 스택
#### Core
- Spring Boot Web
- Spring Data JPA
- PostgreSQL
- Lombok

#### DevOps
- Spring Boot Docker Compose Support

## ✅ 브랜치 전략

### 계층적 브랜치 구조

프로젝트는 계층적 브랜치 전략을 사용하여 기술별 기본 설정과 세부 테스트를 분리합니다.

```
main                          # 최소 설정 (PostgreSQL 연결 포함)
│
├── base/{기술명}              # 기술별 기본 설정 브랜치
│   ├── base/kafka            # Kafka 기본 설정 (Docker, 의존성, Config)
│   ├── base/redis            # Redis 기본 설정
│   ├── base/security         # Spring Security 기본 설정
│
└── {기술명}/{테스트내용}       # 세부 테스트 브랜치
    ├── kafka/producer-basic
    ├── kafka/consumer-error-handling
    ├── kafka/streams-aggregation
    ├── redis/distributed-lock
    └── security/jwt-custom
```

### `main` 브랜치
최소한의 설정만 포함된 루트 브랜치입니다.

**포함된 내용:**
- Spring Boot Web 기본 설정
- PostgreSQL 데이터베이스 연결
- Docker Compose를 통한 PostgreSQL 자동 실행
- Lombok 설정

### `base/{기술명}` 브랜치
특정 기술의 기본 설정을 포함한 브랜치입니다. 해당 기술을 테스트하기 위한 베이스 역할을 합니다.

**예시:**
- `base/kafka`: Kafka Docker 설정, Spring Kafka 의존성, 기본 Producer/Consumer 설정
- `base/redis`: Redis Docker 설정, RedisTemplate 설정
- `base/security`: Spring Security 의존성, 기본 SecurityConfig

### `{기술명}/{테스트내용}` 브랜치
구체적인 기능이나 패턴을 테스트하는 브랜치입니다. </br>
각 브랜치는 해당 기술의 `base/{기술명}` 브랜치에서 분기되어야 합니다.


**명명 규칙:**
- 기술명은 소문자로 작성
- 테스트 내용은 구체적이고 명확하게 작성
- 하이픈(-)으로 단어 구분

### ⚠️ 브랜치 관리 원칙

1. **main은 불변**: 최소 설정만 유지, 함부로 수정하지 않음
2. **base**: 해당 기술의 동작 가능한 최소 설정 유지
3. **유용한 설정은 base로**: 검증된 설정은 base 브랜치에 반영
4. **문서화**: 각 브랜치의 README에 테스트 목적과 결과 기록

## ✅ 실행 방법
- Docker 가 실행 중이어야 합니다.
### 1. 프로젝트 실행

```bash
./gradlew bootRun
```

Spring Boot Docker Compose Support가 설정되어 있어, 애플리케이션 실행 시 자동으로 PostgreSQL 컨테이너가 Compose 로 시작됩니다.


### 2. 애플리케이션 종료 시 컨테이너 정리
애플리케이션이 종료될 때 컨테이너는 종료되지 않아, 수동으로 정리해야 합니다.

```bash
docker-compose -f src/main/resources/compose.yaml down
```