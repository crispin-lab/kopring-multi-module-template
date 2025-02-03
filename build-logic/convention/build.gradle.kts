plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("jvmLibrary") {
            id = "jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("kotestLibrary") {
            id = "kotest.library"
            implementationClass = "KotestLibraryConventionPlugin"
        }
    }
}
