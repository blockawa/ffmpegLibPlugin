# FFmpeg Plugin

为 Android 端 Minecraft Java 版启动器 (FCL) 提供 FFmpeg 原生库支持的插件。

## 功能

打包 FFmpeg / FFmpeg Kit 编译的原生二进制文件（ffmpeg、ffprobe），供 Minecraft Java 版启动器在录制视频、处理媒体时调用。

### 包含的编解码器
- x264 (H.264)
- x265 (H.265/HEVC)
- Opus
- Vorbis
- Theora
- Twolame (MP3)
- Shine (AAC)

### 支持的架构
- armeabi-v7a
- arm64-v8a
- x86
- x86_64

## 构建

### 前置条件
- JDK 17
- Android SDK + NDK
- nasm、autogen

### 构建步骤
```bash
# 1. 克隆并补丁 ffmpeg-kit
./scripts/setup_environ.sh

# 2. 构建 FFmpeg 原生库
./scripts/build_libs.sh

# 3. 构建 APK
./gradlew assembleRelease
```

APK 输出路径：`app/build/outputs/apk/release/`

### CI 构建
推送代码到 `master` 分支或手动触发 GitHub Actions 工作流，将自动完成构建。

## 配置
在 [app/build.gradle.kts](./app/build.gradle.kts) 中可调整以下参数：

| 参数 | 说明 |
|------|------|
| `app_name` | 应用名称 |
| `des` | 插件描述，显示在启动器插件列表中 |
| `environment` | JVM 环境参数，传递原生库路径 |
| `minMCVer` | 最小支持的 Minecraft 版本 |
| `maxMCVer` | 最大支持的 Minecraft 版本 |
