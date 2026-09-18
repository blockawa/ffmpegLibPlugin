#!/bin/bash
export ANDROID_SDK_ROOT=${ANDROID_SDK_ROOT:-$ANDROID_HOME}
export ANDROID_NDK_ROOT=${ANDROID_NDK_ROOT:-$ANDROID_HOME/ndk/27.1.12297006}
if [[ "$PWD" == *scripts ]]; then
   cd ..
fi
if [ ! -e ./ffmpeg-kit ]; then
  echo "No ffmpeg-kit. Did you run setup_environ.sh first?"
  exit 1
fi
cd ffmpeg-kit
./android.sh --api-level=26 --enable-gpl --enable-shine --enable-x264 --enable-x265 --enable-libtheora --enable-opus --enable-libvorbis --enable-twolame
function copy_libs {
   local DEST="../app/src/main/jniLibs/$1"
   if [ -e "$DEST" ]; then
      rm -r "$DEST"/*
   else
      mkdir -p "$DEST"
   fi
   cp -t "$DEST" prebuilt/$2/ffmpeg/bin/* prebuilt/$2/ffmpeg/lib/*
   mv "$DEST/ffmpeg" "$DEST/libffmpeg.so"
   mv "$DEST/ffprobe" "$DEST/libffprobe.so"
   cp android/libs/$1/libc++_shared.so "$DEST/"
}

if [ ! -e ../app/src/main/jniLibs ]; then
      mkdir -p ../app/src/main/jniLibs
fi

copy_libs armeabi-v7a android-arm-neon
copy_libs arm64-v8a android-arm64
copy_libs x86 android-x86
copy_libs x86_64 android-x86_64
