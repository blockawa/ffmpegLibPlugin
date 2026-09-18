# FFmpeg Plugin

为 Android 端 Minecraft Java 版启动器 (FCL) 提供 FFmpeg 原生库支持的插件。

源码移植自 [PojavLauncherTeam/FFmpegPlugin](https://github.com/PojavLauncherTeam/FFmpegPlugin)。

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

## 使用方法
1. 安装此插件 APK
2. 启动游戏，打开 ReplayMod
3. 渲染视频时，在 **Command Line Settings** 的左侧输入框中填入 ffmpeg 路径：
   ```
   {nativeLibraryDir}/libffmpeg.so
   ```
   FCL 启动器会自动将 `{nativeLibraryDir}` 替换为实际的原生库目录路径。
4. 点击渲染即可
