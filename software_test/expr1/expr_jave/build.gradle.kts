plugins {
    id("java")
}

group = "org.napbad.software_test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
//    useJUnitPlatform()
}