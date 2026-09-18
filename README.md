# FFmpeg Plugin

为 Android 端 Minecraft Java 版启动器 (FCL) 提供 FFmpeg 原生库支持的插件。

Plugin移植自 [PojavLauncherTeam/FFmpegPlugin](https://github.com/PojavLauncherTeam/FFmpegPlugin)。

## 构建

### 前置条件
- JDK 17
- Android SDK + NDK
- nasm、autogen

### 构建步骤
```bash
./scripts/setup_environ.sh
./scripts/build_libs.sh
./gradlew assembleRelease
```

APK 输出路径：`app/build/outputs/apk/release/`

### CI 构建
推送代码到 `master` 分支或手动触发 GitHub Actions 工作流即可。
