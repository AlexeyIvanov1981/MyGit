plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // ===== JUnit 5 =====
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // ===== Selenide — UI-тесты =====
    testImplementation("com.codeborne:selenide:7.3.2")

    // ===== REST Assured — API-тесты =====
    testImplementation("io.rest-assured:rest-assured:5.4.0")

    // ===== AssertJ =====
    testImplementation("org.assertj:assertj-core:3.25.3")

    // ===== Allure для JUnit 5 (БЕЗ Gradle-плагина!) =====
    testImplementation("io.qameta.allure:allure-junit5:2.27.0")

    // ===== Lombok =====
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    // Lombok в тестах (если используете аннотации в тестовых классах)
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

    // ===== Jackson для JSON-десериализации =====
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")

    // ===== SLF4J реализация для логов =====
    testImplementation("org.slf4j:slf4j-simple:2.0.16")

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()

    // Куда складывать результаты Allure (папка в корне проекта)
    systemProperty("allure.results.directory", "allure-results")

    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        showCauses = true
    }
}

// Автозагрузка JDK для toolchains
tasks.withType<JavaCompile> {
    options.forkOptions.javaHome = null
}