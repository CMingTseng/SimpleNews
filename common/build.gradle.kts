import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.INT
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.LONG
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.FLOAT


@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    kotlin("native.cocoapods")
    id("kotlinx-serialization")
    id("io.github.skeptick.libres")//i18n
    alias(libs.plugins.ksp)
    id("io.realm.kotlin")
    // Apply Realm s pecific linting plugin to get common Realm linting tasks
//    id("realm-lint")
    id("com.codingfeline.buildkonfig")
}
apply(plugin = "de.jensklingenberg.ktorfit")

group = "rooit.me.xo.common"
version = "1.0-SNAPSHOT"
val podName = "common"
val ktorfit_version= libs.versions.ktorfit.qdsfdhvh.version.get()
val de_ktorfit_version = libs.versions.ktorfit.version.get()

libres {
    generatedClassName = "MainRes" // "Res" by default
    generateNamedArguments = true // false by default
    baseLocaleLanguageCode = "ru" // "en" by default
    camelCaseNamesForAppleFramework = false // false by default
}

buildkonfig {
    packageName = project.group.toString()
    println("Show me  packageName $packageName ")
    objectName="BuildConfig"
    exposeObjectWithName="BuildConfig"


    defaultConfigs {
        buildConfigField(Type.STRING, "test", "testvalue")
        buildConfigField(Type.BOOLEAN, "DEBUG", false.toString())
    }

    targetConfigs {
        create("android") {
            buildConfigField(FieldSpec.Type.STRING, "target", "me_android")
        }
        create("jvm") {
            buildConfigField(FieldSpec.Type.STRING, "target", "jvm")
        }
        create("ios") {
            buildConfigField(FieldSpec.Type.STRING, "target", "ios")
        }
        create("desktop") {
            buildConfigField(FieldSpec.Type.STRING, "desktopvalue", "desktop")
        }
        create("jsCommon") {
            buildConfigField(FieldSpec.Type.STRING, "target", "jsCommon")
        }
    }
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
//    macosX64()

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

                //network
                api(libs.ktor.core)
                api(libs.ktor.json)
                api(libs.ktor.logging)
                api(libs.ktor.negotiation)
                api(libs.ktor.serialization.json)
                api(libs.de.ktorfit)
                //image loading
                api(libs.imageLoader)
                implementation(libs.image)
                api(libs.log)

                implementation(libs.i18n.skeptick.libres)

                implementation("io.realm.kotlin:library-base:1.13.0")
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
                api(libs.ktor.jvm)
            }
        }

//        val jvmTest by getting
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
    with("de.jensklingenberg.ktorfit:ktorfit-ksp:$de_ktorfit_version") {
        add("kspCommonMainMetadata", this)
        add("kspJvm", this)
        add("kspJvmTest", this)
        add("kspAndroid", this)
        add("kspAndroidTest", this)
        add("kspIosX64", this)
        add("kspIosX64Test", this)
        add("kspIosArm64", this)
        add("kspIosArm64Test", this)
        add("kspIosSimulatorArm64", this)
        add("kspIosSimulatorArm64Test", this)
//        add("kspMacosX64", this)
//        add("kspMacosX64Test", this)
//        add("kspJs", this)
//        add("kspJsTest", this)
    }
}

android {
    namespace ="rooit.me.xo.common"
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
