# Gemma 4

An Android chat app that runs **Gemma 4 E2B** entirely on-device using [LiteRT-LM](https://developers.google.com/edge/litert-lm/android). No network connection is required after the model is installed.

## Features

- On-device inference with the bundled `gemma-4-E2B-it.litertlm` model
- Streaming chat responses via the LiteRT-LM Kotlin API
- GPU acceleration with Multi-Token Prediction (MTP), falling back to CPU when GPU is unavailable
- Material 3 chat UI built with Jetpack Compose

## Requirements

| Requirement | Details |
|-------------|---------|
| Device | Physical Android device recommended (emulator may not have enough RAM) |
| Android | API 24+ (Android 7.0) |
| RAM | 8 GB+ recommended for the 2B model |
| Storage | ~5 GB free (APK + one-time model copy to internal storage) |
| Model file | `gemma-4-E2B-it.litertlm` (~2.5 GB) |

## Model setup

The model is not committed to git. Place it in:

```
app/src/main/assets/gemma-4-E2B-it.litertlm
```

Compatible `.litertlm` models are available from the [HuggingFace LiteRT Community](https://huggingface.co/litert-community).

## Getting started

1. Clone the repository and add the model file to `app/src/main/assets/`.
2. Open the project in Android Studio or build from the command line:

```bash
.\gradlew.bat :app:assembleDebug
```

3. Install on a connected device:

```bash
.\gradlew.bat :app:installDebug
```

4. Launch **Gemma 4** on the device.

### First launch

On the first run, the app:

1. Copies the model from assets to internal storage (progress bar shown)
2. Initializes the LiteRT-LM engine (may take up to a minute)
3. Opens the chat screen

Subsequent launches skip the copy step and load faster thanks to engine caching.

## Architecture

```
app/src/main/java/com/mlab/gemma4/
├── MainActivity.kt              # Entry point
├── chat/
│   ├── ChatScreen.kt            # Scaffold and screen routing
│   ├── ChatViewModel.kt         # UI state and message handling
│   ├── ChatMessage.kt           # Immutable message model
│   └── components/              # Compose UI (atoms, molecules, organisms)
└── litert/
    ├── ModelPreparer.kt         # Copies asset model to filesDir
    ├── LiteRtChatSession.kt     # Engine + conversation lifecycle
    └── MessageText.kt           # Extracts display text from LiteRT messages
```

### LiteRT-LM integration

- **Dependency:** `com.google.ai.edge.litertlm:litertlm-android:latest.release`
- **Backend:** GPU with speculative decoding (MTP), CPU fallback
- **Inference:** `sendMessageAsync()` Kotlin Flow for streaming tokens
- **Manifest:** GPU native libraries (`libOpenCL.so`, `libvndksupport.so`) declared as optional

## Tech stack

- Kotlin 2.2
- Jetpack Compose + Material 3
- LiteRT-LM Android SDK
- Android Gradle Plugin 9.4
- minSdk 24 / targetSdk 37

## Build notes

- `.litertlm` files are excluded from APK compression (`noCompress`) to avoid packaging issues with large models.
- `android:largeHeap="true"` is enabled to support model memory requirements.
- Debug builds disable R8 optimization for faster iteration.

## References

- [Get Started with LiteRT-LM on Android](https://developers.google.com/edge/litert-lm/android)
- [LiteRT-LM on Google Maven](https://maven.google.com/web/index.html#com.google.ai.edge.litertlm:litertlm-android)
- [Google AI Edge Gallery](https://github.com/google-ai-edge/gallery) — reference app for on-device GenAI

## License

Model weights are subject to the [Gemma license](https://ai.google.dev/gemma/terms). Application code follows the project's license terms.