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


# @RefreshScope

사용으로 인해 SpringBootTest 를 WebMvcTest로 변경함

refreshscope needs the complete container to work and when we use webmvctest it does not use the complete container. webmvctest does not load all the configurations for spring. probably because of this the refreshscope wasn't loaded.
