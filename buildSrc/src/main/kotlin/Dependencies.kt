import org.gradle.api.JavaVersion

object Config {
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val compileSdkVersion = 33
    const val composeCompilerExtension = "1.4.6"
    val javaVersion = JavaVersion.VERSION_17
}

object Versions {
    const val grade = "8.0.0"
    const val kotlin = "1.8.20"

    const val androidXCore = "1.10.0"
    const val androidXLifecycle = "2.6.1"
    const val androidXActivity = "1.7.1"
    const val androidXComposeUi = "1.4.2"
    const val androidXBOM = "2023.05.00"

    const val junitJupiter = "5.9.2"
}

object Plugins {
    val gradle by lazy { "com.android.tools.build:gradle:${Versions.grade}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Deps {
    val androidXCore by lazy { "androidx.core:core:${Versions.androidXCore}" }
    val androidXLifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidXLifecycle}" }
    val androidXActivityCompose by lazy { "androidx.activity:activity-compose:${Versions.androidXActivity}" }
    val androidXViewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidXLifecycle}" }
    val androidXComposeUi by lazy { "androidx.activity:activity-compose:${Versions.androidXComposeUi}" }
    val androidXComposeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val androidXComposeMaterial3 by lazy { "androidx.compose.material3:material3" }
    val androidXBOM by lazy { "androidx.compose:compose-bom:${Versions.androidXBOM}" }

    val androidXCompuseUiTestJUnit4 by lazy { "androidx.compose.ui:ui-test-junit4" }
    val junitJupiterApi by lazy {"org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"}
    val junitJupiterEngine by lazy {"org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"}
    val junitJupiterVintage by lazy {"org.junit.vintage:junit-vintage-engine:${Versions.junitJupiter}"}

    val androidXCompuseUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val androidXCompuseUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest" }
}