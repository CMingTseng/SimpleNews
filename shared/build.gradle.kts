import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id ("org.jetbrains.kotlin.plugin.serialization")
    alias(libs.plugins.jetbrains.compose.hot.reload)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                    freeCompilerArgs.add("-Xjdk-release=${JavaVersion.VERSION_1_8}")
                }
            }
        }
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_1_8)
//        }
    }

//    jvm("desktop") {
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_17)
//        }
//    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//        }
//    }
//
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        browser {
//            val rootDirPath = project.rootDir.path
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(rootDirPath)
//                        add(projectDirPath)
//                    }
//                }
//            }
//        }
//    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)

//                implementation(libs.androidx.activity.compose)

                //jetbrains  compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.jetbrains.androidx.lifecycle.viewmodel)
                implementation(libs.jetbrains.androidx.lifecycle.viewmodel.compose)
                implementation(libs.jetbrains.androidx.navigation.compose)
                implementation(compose.preview)


                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)

                implementation(libs.coil.core)
                implementation(libs.coil)
                implementation(libs.coil.compose)
                implementation(libs.coil.compose.core)
                implementation(libs.coil.network.ktor3)
                implementation(libs.coil.svg)

                implementation(libs.urlencoder.lib)
                implementation(libs.napier)

                implementation(project(":common"))
            }

            commonTest.dependencies {
                implementation(libs.kotlin.test)
            }

            androidMain {
                dependencies {
                    implementation(libs.androidx.activity.compose)
                }
            }

            val androidUnitTest by getting {
                dependencies {
                    implementation(libs.androidx.junit)
                    implementation(libs.androidx.espresso.core)
                }
            }

            val androidInstrumentedTest by getting {
                dependencies {
                    implementation(libs.androidx.junit)
                    implementation(libs.androidx.espresso.core)
                }
            }
        }
    }
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


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
}

compose.resources {
    publicResClass = true
    packageOfResClass = "rooit.me.xo"
    generateResClass = always
}