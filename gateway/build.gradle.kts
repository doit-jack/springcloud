
dependencies {
	implementation(project(":core"))

	implementation("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}