plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "org.napbad"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}


val springVersion = "3.2.5"
val springCloudLoadBalancerVersion = "4.1.3"
val jimmerVersion = "0.9.25"
val druidVersion = "1.2.21"
val jwtVersion = "0.9.1"
val jaxbVersion = "4.0.2"
val jaxbRuntimeVersion = "2.3.1"
val lombokVersion = "1.18.32"
val aspectVersion = "1.9.21"
val junitPlatformLauncherVersion = "1.10.2"
val javaMailVersion = "1.6.2"
val commonsPoolVersion = "2.11.1"
val aliOOSVersion = "3.17.4"
val jsrVersion = "2.17.2"


dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web")

	// Data
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:$jimmerVersion")
	implementation("org.babyfish.jimmer:jimmer-sql:$jimmerVersion")
	implementation("com.alibaba:druid:$druidVersion")
	implementation("com.aliyun.oss:aliyun-sdk-oss:$aliOOSVersion")
	runtimeOnly("com.mysql:mysql-connector-j")

	// Utils
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jsrVersion")
	implementation("io.jsonwebtoken:jjwt:$jwtVersion")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.babyfish.jimmer:jimmer-apt:$jimmerVersion")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
