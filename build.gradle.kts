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
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}