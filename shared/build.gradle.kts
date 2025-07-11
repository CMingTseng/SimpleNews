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
        // 或者，如果你喜欢更简洁的 disable 方式 (两者等效):
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
        flavorDimensions.add("product_code")
        // flavorDimensions = mutableSetOf("product_code")
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

    productFlavors {
        create("legacy_nav") {
            isDefault=true
            dimension = "product_code" // 直接赋值
            dependencies {
                implementation(libs.androidx.fragment.ktx)
                implementation(libs.material)
                implementation(libs.androidx.constraintlayout)
                implementation(libs.androidx.navigation.fragment.ktx)
                implementation(libs.splashscreen)
                implementation(libs.androidx.lifecycle.livedata.ktx)
                implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
                implementation(libs.glide)
                ksp(libs.glide.ksp)
            }
        }

        create("cmp_kmp") {
            dimension = "product_code"
            dependencies {
                implementation(libs.androidx.navigation.fragment.ktx)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.androidx.lifecycle.runtime.compose)

                implementation(libs.androidx.activity.compose)
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.androidx.compose.ui)
                implementation(libs.androidx.compose.ui.graphics)
                implementation(libs.androidx.compose.ui.tooling.preview)
                implementation(libs.androidx.compose.foundation)

                implementation(libs.androidx.compose.material)
                implementation(libs.androidx.compose.material3)
                implementation(libs.androidx.navigation.compose)

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
            }
        }
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java")
            res.srcDirs("src/main/res")
            manifest.srcFile("src/main/AndroidManifest.xml")
        }

        getByName("legacy_nav") {
            java.srcDirs("src/legacy_nav/java")
            res.srcDirs("src/legacy_nav/res")
            manifest.srcFile("src/legacy_nav/AndroidManifest.xml")
        }
        getByName("cmp_kmp") {
            java.srcDirs("src/cmp_kmp/java")
            res.srcDirs("src/cmp_kmp/res")
            manifest.srcFile("src/cmp_kmp/AndroidManifest.xml")
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines)

//    add("legacy_navImplementation", libs.androidx.fragment.ktx)
//    add("cmp_kmpImplementation", libs.androidx.lifecycle.runtime.ktx)
//    add("kspLegacy_nav", libs.glide.ksp)
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

    implementation(libs.urlencoder.lib)

    implementation(libs.realm.kotlin.base)
//    implementation (libs.realm.kotlin.sync)

    implementation(libs.koin.core)
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}