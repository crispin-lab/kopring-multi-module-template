package com.crispinlab

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.testing.Test
import org.gradle.internal.extensions.core.extra
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.plugins(vararg pluginId: String) {
    with(this.pluginManager) {
        pluginId.forEach(::apply)
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(17)
    }
}

internal fun Project.configureTestTask() {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

internal fun Project.linterGitHooksInstall() {
    if (!rootProject.extra.has("install-linter-git-hooks")) {
        rootProject.extra["install-linter-git-hooks"] = true

        val preCommit: TaskProvider<InstallPreCommitHookTask> =
            rootProject.tasks.register<InstallPreCommitHookTask>(
                "installPreCommitHook",
                fun InstallPreCommitHookTask.() {
                    group = "build setup"
                    description = "Installs Kotlinter Git pre-commit hook"
                }
            )

        val prePush: TaskProvider<InstallPrePushHookTask> =
            rootProject.tasks.register<InstallPrePushHookTask>(
                "installPrePushHook",
                fun InstallPrePushHookTask.() {
                    group = "build setup"
                    description = "Installs Kotlinter Git pre-push hook"
                }
            )

        rootProject.tasks.named("prepareKotlinBuildScriptModel").configure {
            dependsOn(preCommit, prePush)
        }
    }
}
