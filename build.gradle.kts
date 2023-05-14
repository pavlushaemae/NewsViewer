// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0-alpha03" apply false
    id ("com.android.library") version "8.2.0-alpha03" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    kotlin("kapt") version "1.8.10"
}

detekt {
    source.setFrom(files(projectDir))
    config.setFrom(files("${project.rootDir}/config/detekt/detekt.yml"))
    parallel = true
}
