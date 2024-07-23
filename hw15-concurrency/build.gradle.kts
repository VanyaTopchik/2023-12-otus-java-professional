plugins {
    id("java")
}

group = "ru.otus"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}