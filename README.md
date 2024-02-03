# Spring Cloud

Spring Boot + Spring Cloud 스프링 부트가 필수.

- 환경설정 정보: Spring Cloud Config Server
- 서비스 등록/ 위치 정보 확인 : Eureka
- Load Balancing : Spring Cloud Gateway
- 통신 : FeignClient -> Spring 3.0의 HTTP Framework 사용해봐도 될 듯
- 모니터링 : Netflix API gateway
- Fault Tolerance: Hystrix

# Spring Cloud Eureka

- key, value 형태로 서비스 목록 관리
- 각 서비스를 등록 후 Client의 요청이 Load Balancer 를 통해 각 서비스로 이동

## gradle 설명

https://techblog.woowahan.com/2625/

# API Gateway Service

![img.png](image/img.png)

- 인증 및 권한 부여
- 서비스 검색 통합
- 응답 캐싱
- 정책, 회로 차단기 및 QoS 다시 시도
- 속도 제한
- 부하 분산
- 로깅, 추적, 상관 관계
- 헤더, 쿼리 문자열 및 청구 변환
- IP 허용 목록에 추가

# Netflix Ribbon

- Spring Cloud에서의 MSA간 통신

  - RestTemplate
  - Feign Client
- Ribbon: **Client Side** Load Balancer

  - 로드 밸런싱을 위해 Ribbon 을 사용했으나, 비동기 지원이 안되어 사용 안함
  - 서비스 이름으로 호출
  - Health Check
  - 클라이언트 사이드에 심어진다.

# Netflix Zuul

- Middleware로 동작

But , ribbon, zuul 모두 maintenance 상태로 -> spring cloud gateway 쓰라고 되어 있음

# Spring Cloud

@ConfigurationProperties - Refresh 될 때 항상 Bean이 재생성 됨

vs

@RefreshScope - 해당 Bean은 프록시로 생성되고, 실제 Bean을 캐시에 저장. /refresh 될 때, Proxy의 bean이 destroy되고 캐시의 값을 초기화.

# Kafka

```bash
docker-compose -f docker-compose.yml up -d

kafka-topics --bootstrap-server localhost:9092 --list

kafka-console-consumer --bootstrap-server localhost:9092 --topic my_topic_users --from-beginning
```

![image.png](assets/kafka.png)

![image.png](assets/kafka2.png)

## 설치 과정

https://velog.io/@imnooy/Kafka-Connect-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0

```bash
brew tap confluentinc/homebrew-confluent-hub-client

brew install --cask confluent-hub-client

confluent-hub install confluentinc/kafka-connect-jdbc:latest --component-dir /Users/jack/99_Study/kafka-connect/component --worker-configs /Users/jack/99_Study/kafka-connect/config/worker.properties
```
