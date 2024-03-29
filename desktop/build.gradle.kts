import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    alias(libs.plugins.ksp)
    id("com.codingfeline.buildkonfig")
}


group = "rooit.me.xo.app"
version = "1.0-SNAPSHOT"

buildkonfig {
    packageName = project.group.toString()
    println("Show me  packageName $packageName ")
    objectName = "BuildConfig"
    exposeObjectWithName = "BuildConfig"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.BOOLEAN, "DEBUG", false.toString())
        buildConfigField(FieldSpec.Type.STRING, "BASE_URL", "https://newsapi.org/")

    }
}
//Ref : https://jeroenmols.com/blog/2021/03/17/share-code-kotlin-multiplatform/
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
//        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        jvmMain {
//        val jvmMain by getting {
            kotlin.srcDir("../common_ui/src/commonMain/kotlin")

//        }
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)
                implementation(libs.skiko)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "rooit.me.xo.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "common"
            packageVersion = "1.0.0"
        }
    }
}
