@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("io.github.skeptick.libres")//i18n
    alias(libs.plugins.ksp)
}

group = "rooit.me.xo"
version = "1.0-SNAPSHOT"
val podName = "common_ui"

libres {
    generatedClassName = "MainRes" // "Res" by default
    generateNamedArguments = true // false by default
    baseLocaleLanguageCode = "ru" // "en" by default
    camelCaseNamesForAppleFramework = false // false by default
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm {
        jvmToolchain(17)
    }
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = podName
            isStatic = true
        }
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                //kotlin
                api(libs.kotlinx.serialization)
                api(libs.kotlinx.coroutines)

                //compose
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.materialIconsExtended)
                api(compose.material3)

                //DI
                api(libs.koin.core)
                api(libs.koin.jb.compose)

                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)

                //image loading
                api(libs.imageLoader)
                implementation(libs.image)
                api(libs.log)

                implementation(libs.i18n.skeptick.libres)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.core.ktx)
                api(libs.androidx.activity.compose)
                implementation(libs.ktor.android)
                api(libs.androidx.lifecycle.runtime.ktx)
                compileOnly(libs.androidx.ui.tooling.preview)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                api(libs.ktor.ios)
                api(libs.stately)
            }
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val jvmMain by getting {
            dependencies {
                api(compose.preview)
            }
        }

        val jvmTest by getting
    }

    tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }
    explicitApi()
}

ksp {
    arg("lyricist.generateStringsProperty", "true")
}

dependencies {
}

android {
    namespace ="rooit.me.xo.ui"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
