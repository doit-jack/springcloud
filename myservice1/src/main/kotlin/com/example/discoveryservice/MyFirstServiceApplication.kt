package com.example.firstservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication // 해당 어노테이션이 들어 있는 파일을 실행해준다!
@EnableDiscoveryClient
class MyFirstServiceApplication

fun main(args: Array<String>) {
    runApplication<MyFirstServiceApplication>(*args)
}
