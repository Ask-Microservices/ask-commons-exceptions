plugins {
    `java-library`
    `maven-publish`
}

group = "pe.ask.commons.exceptions"
version = "1.0.0"
description = "Common exception library for Ask projects"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("")
}

repositories {
    mavenCentral()
}

dependencies {
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(project.description)
            }
        }
    }
    repositories {
        maven {
            name = "LocalRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<GenerateModuleMetadata> {
    enabled = true
}
