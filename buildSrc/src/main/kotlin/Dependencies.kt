import org.gradle.api.JavaVersion

object Config {
    const val minSdkVersion = 21
    const val targetSdkVersion = 33
    const val compileSdkVersion = 33
    const val composeCompilerExtension = "1.4.6"
    val javaVersion = JavaVersion.VERSION_17
}

object Versions {
    const val grade = "8.0.2"
    const val kotlin = "1.8.20"

    const val androidXCore = "1.10.0"
    const val androidXLifecycle = "2.6.1"
    const val androidXActivity = "1.7.1"
    const val androidXComposeUi = "1.4.2"
    const val androidXComposeMaterial3 = "1.0.1"
    const val mavericks = "3.0.3"
}

object Plugins {
    val gradle by lazy { "com.android.tools.build:gradle:${Versions.grade}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Deps {
    val androidXCore by lazy { "androidx.core:core:${Versions.androidXCore}" }
    val androidXLifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidXLifecycle}" }
    val androidXActivityCompose by lazy { "androidx.activity:activity-compose:${Versions.androidXActivity}" }
    val androidXComposeUi by lazy { "androidx.activity:activity-compose:${Versions.androidXComposeUi}" }
    val androidXComposeToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.androidXComposeUi}" }
    val androidXComposeMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.androidXComposeMaterial3}" }

    val androidXCompuseUiTestJUnit4 by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.androidXComposeUi}" }

    val androidXCompuseUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.androidXComposeUi}" }
    val androidXCompuseUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.androidXComposeUi}" }

    val mavericksCompose by lazy { "com.airbnb.android:mavericks-compose:${Versions.mavericks}" }
}