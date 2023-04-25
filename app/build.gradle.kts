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

// region Deps
dependencies {

    // region Libraries
    implementation(Deps.androidXCore)
    implementation(Deps.androidXLifecycleRuntime)
    implementation(Deps.androidXActivityCompose)
    implementation(Deps.androidXComposeUi)
    implementation(Deps.androidXComposeToolingPreview)
    implementation(Deps.androidXComposeMaterial3)
    // endregion

    // region Test libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(Deps.androidXCompuseUiTestJUnit4)
    // endregion

    // region Debug Libraries
    debugImplementation(Deps.androidXCompuseUiTooling)
    debugImplementation(Deps.androidXCompuseUiTestManifest)
    // endregion
}
// enregion