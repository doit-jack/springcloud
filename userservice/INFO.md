아래 2개의 방법으로 다른 포트로 동일 서비스를 띄울 수 있다.

- Intellij Confugration 에서 -Dserver.port=9002 VM 옵션 추가

- `gradle bootRun --args='--server.port=9003'`

but 매번 작성하기 귀찮다. Spring Boot에서 이를 자동으로 할 수 있음. 

[appllication.yaml]
```yaml
server:
  port: 0 # 0을 이용하면 랜덤 포트 이용

eureka:
  # 0번으로 해주면 eureka에서 포트가 0으로 나와서 구분이 안되어 해당 설정 해줌
  instance:
    instance-id: ${spring.cloud.client.hostname}:${spring.application.instance_id:${random.value}}
```

`gradle bootRun`