plugins {
    id("java")
}

group = "ru.otus"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}