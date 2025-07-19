plugins {
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id ("org.jetbrains.kotlin.plugin.serialization")
    id("de.jensklingenberg.ktorfit")
    alias(libs.plugins.room)
    alias(libs.plugins.skie)
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "rooit.me.xo"
        compileSdk = 36
        minSdk = 21

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

//    jvm("desktop")

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
//    val xcfName = "commonKit"
//
//    iosX64 {
//        binaries.framework {
//            baseName = xcfName
//        }
//    }
//
//    iosArm64 {
//        binaries.framework {
//            baseName = xcfName
//        }
//    }
//
//    iosSimulatorArm64 {
//        binaries.framework {
//            baseName = xcfName
//        }
//    }
    // 整合 iOS Framework
//    val xcf = XCFramework()
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            xcf.add(this)
//        }
//    }



//    macosX64()
//    macosArm64()
//    wasmJs {
//        browser()
//    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)



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
//    implementation(libs.androidx.navigation.compose)
//    implementation(libs.androidx.lifecycle.runtime.compose)

                implementation(libs.androidx.activity.compose)
                //JetPack compose
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.compose.ui)
//    implementation(libs.androidx.compose.ui.graphics)
//    implementation(libs.androidx.compose.ui.tooling.preview)
//    implementation(libs.androidx.compose.foundation)
//    implementation(libs.androidx.compose.material)
//    implementation(libs.androidx.compose.material3)

                //jetbrains  compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.jetbrains.androidx.lifecycle.viewmodel.compose)
                implementation(libs.jetbrains.androidx.navigation.compose)
                implementation(compose.preview)

                implementation(libs.koin.core)
//    implementation(libs.koin.android)
//    implementation(libs.koin.androidx.compose)
//    implementation(libs.koin.androidx.compose.navigation)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)

                implementation(libs.androidx.room.runtime)

                implementation(libs.androidx.room.ktx)
                implementation(libs.androidx.paging.common)
                implementation(libs.androidx.paging.compose.android)
                implementation(libs.sqlite.bundled)

                implementation(libs.androidx.datastore.preferences.core)
                implementation(libs.androidx.datastore.core.okio)
                implementation(libs.okio)
                implementation(libs.kermit)
                implementation(libs.kotlinx.atomicfu)
                implementation(libs.napier)

            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
                implementation(libs.androidx.room.paging)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
//                implementation(libs.androidx.runner)
//                implementation(libs.androidx.core)
//                implementation(libs.androidx.junit)
            }
        }

//        iosMain {
//            dependencies {
//                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
//                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
//                // part of KMP’s default source set hierarchy. Note that this source set depends
//                // on common by default and will correctly pull the iOS artifacts of any
//                // KMP dependencies declared in commonMain.
//            }
//        }

//        val iosMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.darwin)
//            }
//        }
//        val desktopMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.cio)
//            }
//        }
//        val wasmJsMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.js)
//            }
//        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

skie {
    features {
        // https://skie.touchlab.co/features/flows-in-swiftui
        enableSwiftUIObservingPreview = true
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.androidx.room.compiler)


    // 平台特定的 KSP
    add("kspAndroid", libs.androidx.room.compiler)
//    add("kspIosX64", libs.androidx.room.compiler)
//    add("kspIosArm64", libs.androidx.room.compiler)
//    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
//    add("kspDesktop", libs.androidx.room.compiler)
//    add("kspMacosX64", libs.androidx.room.compiler)
//    add("kspMacosArm64", libs.androidx.room.compiler)
//    add("kspWasmJs", libs.androidx.room.compiler)
}


//android {
//    namespace = "rooit.me.xo"
//    compileSdk = 36
//    lint {
//        warningsAsErrors = false
//        abortOnError = true
//        checkAllWarnings = true
//        ignoreWarnings = false
//        checkDependencies = true
//        htmlReport = true
//        explainIssues = true
//        noLines = false
//        disable.addAll(
//            listOf(
//                "MissingTranslation",
//                "ExtraTranslation",
//                "TypographyEllipsis",
//                "UnspecifiedImmutableFlag",
//                "UnusedResources",
//                "TypographyDashes"
//            )
//        )
//    }
//
//    buildFeatures {
//
//        buildConfig = false
//        compose = true
//    }
//
//    defaultConfig {
//        minSdk = 21
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {//TODO Ref : https://developer.android.com/reference/tools/gradle-api/8.3/null/com/android/build/api/dsl/BuildType
//        getByName("debug") {
//            matchingFallbacks.addAll(listOf("release", "debug")) //    matchingFallbacks = listOf("release", "debug")
//            isMinifyEnabled = false
//            isShrinkResources = false // 注意: isShrinkResources 依赖 isMinifyEnabled = true
//            (this as com.android.build.gradle.internal.dsl.BuildType).isDebuggable = true
//            proguardFiles(
//                getDefaultProguardFile("proguard-android.txt"),
//                "proguard-system-common.pro",
//                "proguard-third-party.pro",
//                "proguard-module.pro"
//            )
//        }
//
//        getByName("release") {
//            matchingFallbacks.addAll(listOf("release", "debug")) //    matchingFallbacks = listOf("release", "debug")
//            isMinifyEnabled = false
//            isShrinkResources = false
//            (this as com.android.build.gradle.internal.dsl.BuildType).isDebuggable = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//

//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//
//    kotlinOptions {
//        jvmTarget = JavaVersion.VERSION_1_8.toString()
//    }
//
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
//}

dependencies {


//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}