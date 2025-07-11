plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id ("org.jetbrains.kotlin.plugin.serialization")
    id ("io.realm.kotlin")
    id("de.jensklingenberg.ktorfit")
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.timber)

    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.json)

    implementation(libs.de.ktorfit)
    implementation(libs.de.ktorfit.converters.call)
    implementation(libs.de.ktorfit.converters.flow)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    implementation(libs.realm.kotlin.base)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}