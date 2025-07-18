plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "rooit.me.xo"
    compileSdk = 36
    lint {
        warningsAsErrors = true
        abortOnError = true
        disable.addAll(
            listOf(
                "MissingTranslation",
                "ExtraTranslation",
                "TypographyEllipsis",
                "UnspecifiedImmutableFlag",
                "UnusedResources",
                "TypographyDashes"
            )
        )

        // disable("MissingTranslation",
        //         "ExtraTranslation",
        //         "TypographyEllipsis",
        //         "UnspecifiedImmutableFlag",
        //         "UnusedResources",
        //         "TypographyDashes")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = false
        compose = true
    }

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    //FIXME !! Ref : Android Gradle Plugin 7 LibraryBuildType no longer has isDebuggable property
    buildTypes {//TODO Ref : https://developer.android.com/reference/tools/gradle-api/8.3/null/com/android/build/api/dsl/BuildType
        getByName("debug") {
            matchingFallbacks.addAll(listOf("release", "debug")) //    matchingFallbacks = listOf("release", "debug")
            isMinifyEnabled = false
            isShrinkResources = false // 注意: isShrinkResources 依赖 isMinifyEnabled = true
            (this as com.android.build.gradle.internal.dsl.BuildType).isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-system-common.pro",
                "proguard-third-party.pro",
                "proguard-module.pro"
            )

            // proguardFiles = mutableListOf(
            //     getDefaultProguardFile("proguard-android.txt"),
            //     "proguard-system-common.pro",
            //     "proguard-third-party.pro",
            //     "proguard-module.pro"
            // )
        }
        getByName("release") {
            matchingFallbacks.addAll(listOf("release", "debug")) //    matchingFallbacks = listOf("release", "debug")
            isMinifyEnabled = false
            isShrinkResources = false
            (this as com.android.build.gradle.internal.dsl.BuildType).isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        /*
        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".staging"
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules-staging.pro")
        }
        */
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)


    implementation(libs.androidx.activity.compose)
    //Jetpack compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)


    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)

    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    implementation(libs.coil.core)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.compose.core)
    implementation(libs.coil.network.ktor3)
    implementation(libs.coil.svg)
    implementation(project(":common"))
    implementation(libs.urlencoder.lib)

    implementation(libs.napier)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}