/*
 * Copyright 2026 Uli Bubenheimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(libs.versions.java.toolchain.get())
    }
}

android {
    namespace = "org.bubenheimer.adbtile"

    buildFeatures {
        buildConfig = true
    }

    libs.versions.android.sdk.compile.get().let {
        it.toAndroidVersion()?.let { (major, minor, ext) ->
            compileSdk = major
            compileSdkMinor = minor
            compileSdkExtension = ext
        } ?: run { compileSdkPreview = it }
    }

    compileOptions {
        JavaVersion.toVersion(libs.versions.java.android.get()).let {
            sourceCompatibility = it
            targetCompatibility = it
        }
    }

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()

        libs.versions.android.sdk.target.get().let {
            it.toIntOrNull()?.let { targetSdk = it } ?: run { targetSdkPreview = it }
        }

        versionName = "1.4"
        versionCode = 8
    }

    signingConfigs {
        named("debug") {
            enableV3Signing = true
            enableV4Signing = true
        }

        register("release") {
            enableV3Signing = true
            enableV4Signing = true

            val keystorePropertiesFile: String by project

            file(keystorePropertiesFile).takeIf { it.isFile }?.let { file ->
                Properties(4).apply { file.bufferedReader().use { load(it) } }
            }?.let { properties ->
                storeFile = File(properties.getValue("storeFile") as String)
                storeType = "jks"
                storePassword = properties.getValue("storePassword") as String
                keyAlias = properties.getValue("keyAlias") as String
                keyPassword = properties.getValue("keyPassword") as String
            }
        }
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false
        }

        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")
        }
    }

    enableKotlin = false
}

private fun String.toAndroidVersion(): Triple<Int, Int?, Int?>? =
    """\s*(?<major>\d+)(?:\.(?<minor>\d+))?(?:-?ext(?<ext>\d+))?\s*"""
        .toRegex()
        .matchEntire(this)
        ?.destructured
        ?.let { (major, minor, ext) ->
            Triple(major.toInt(), minor.toIntOrNull(), ext.toIntOrNull())
        }
