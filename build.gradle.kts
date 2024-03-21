plugins {
    java
}

group = "se.kry.demo"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.assertj.bom))
    testImplementation(platform(libs.junit.bom))
    testImplementation(platform(libs.slf4j.bom))
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(libs.commons.lang3)
    testImplementation("org.slf4j:slf4j-simple")

    testImplementation(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}