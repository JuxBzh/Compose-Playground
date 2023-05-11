plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    namespace = "com.jux.composeplayground"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = "com.jux.composeplayground"
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(Config.javaVersion)
        targetCompatibility(Config.javaVersion)
    }
    kotlinOptions {
        jvmTarget = Config.javaVersion.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.composeCompilerExtension
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// region Deps
dependencies {

    // region Libraries
    implementation(Deps.androidXCore)
    implementation(Deps.androidXLifecycleRuntime)
    implementation(Deps.androidXActivityCompose)
    implementation(Deps.androidXViewModelCompose)
    implementation(Deps.androidXNavigation)

    implementation(platform(Deps.androidXBOM))
    implementation(Deps.androidXComposeUi)
    implementation(Deps.androidXComposeToolingPreview)
    implementation(Deps.androidXComposeMaterial3)
    // endregion

    // region Test libraries
    testImplementation(Deps.junit)
    testImplementation(Deps.junitJupiterApi)
    testImplementation(Deps.junitJupiterEngine)
    testImplementation(Deps.junitJupiterVintage)

    androidTestImplementation(platform(Deps.androidXBOM))
    androidTestImplementation(Deps.androidXJUnitExt)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.espressoIntents)
    androidTestImplementation(Deps.androidXCompuseUiTestJUnit4)
    androidTestImplementation(Deps.androidXNavigationTesting)
    // endregion

    // region Debug Libraries
    debugImplementation(Deps.androidXCompuseUiTooling)
    debugImplementation(Deps.androidXCompuseUiTestManifest)
    // endregion
}
// enregion