plugins {
    java
}

group = "org.example"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-cli:commons-cli:1.9.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

tasks.test {
    useJUnitPlatform()
}

// Таск для создания нормального jarника, чтобы при запуске не было ошибок вида "no main manifest attribute, in build/libs/ShiftTask.jar"
tasks.register<Jar>("fatJar") {
    archiveBaseName.set("ShiftTask")
    archiveClassifier.set("")
    archiveVersion.set("")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}


// Сделать fatJar задачей по умолчанию при сборке
tasks.build {
    dependsOn(tasks.named("fatJar"))
}
