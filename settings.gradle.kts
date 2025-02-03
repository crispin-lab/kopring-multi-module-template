rootProject.name = "kopring-multi-module-template"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencyResolutionManagement {
        repositories {
            mavenCentral()
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
