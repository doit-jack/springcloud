plugins {
    kotlin("plugin.jpa") version "1.8.22"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    implementation(project(":core"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.modelmapper:modelmapper:2.4.4")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
