plugins {
    id("java")
}

group = "ru.otus"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("ch.qos.logback:logback-classic")
    implementation ("org.flywaydb:flyway-core")
    implementation ("org.postgresql:postgresql")
    implementation ("com.google.code.findbugs:jsr305")
    implementation ("org.projectlombok:lombok")

    implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
}

tasks.test {
    useJUnitPlatform()
}