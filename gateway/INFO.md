```
implementation("org.springframework.cloud:spring-cloud-starter-gateway")
```

해당 의존성을

```
implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc")
```

로 하면,

```
          filters:
            - RewritePath=/first-service/(?<segment>.*),/$\{segment}
```

부분이 동작하지 않는다.
