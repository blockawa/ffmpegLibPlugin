plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.commonlauncher.nativeplugin"
    compileSdk = 34

    signingConfigs {
        create("releaseBuild") {
            storeFile = file("common_plugin.jks")
            storePassword = "common_plugin"
            keyAlias = "common_plugin"
            keyPassword = "common_plugin"
        }
    }

    defaultConfig {
        applicationId = "net.kdt.pojavlaunch.ffmpeg"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("releaseBuild")
        }
        configureEach {
            //应用名
            resValue("string","app_name","FFmpeg Plugin")

            //插件包在启动器内显示的名称
            manifestPlaceholders["des"] = "Provides FFmpeg (ffmpeg/ffprobe) native binaries for media processing"

            //JVM环境参数配置 - 传递FFmpeg二进制文件路径
            //注：{nativeLibraryDir}后无需加"/"，启动器会自动拼接
            manifestPlaceholders["environment"] = mutableMapOf<String,String>().apply {
                put("ffmpeg.exec.path", "{nativeLibraryDir}libffmpeg.so")
                put("ffprobe.exec.path", "{nativeLibraryDir}libffprobe.so")
            }.run {
                buildList {
                    this@run.forEach { (key, value) ->
                        add("-D$key=$value")
                    }
                }.joinToString(" ")
            }

            //最小支持的MC版本
            manifestPlaceholders["minMCVer"] = ""
            //最大支持的MC版本
            manifestPlaceholders["maxMCVer"] = ""
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
}