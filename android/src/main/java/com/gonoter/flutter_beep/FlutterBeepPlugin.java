package com.gonoter.flutter_beep;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterBeepPlugin */
public class FlutterBeepPlugin implements FlutterPlugin, MethodCallHandler {
  /**
   * Cap for tones that ToneGenerator defines as continuous (DTMF, dial, busy, ring, error...).
   * ToneGenerator plays min(duration, intrinsic length), so finite tones keep their own length.
   */
  private static final int DEFAULT_DURATION_MS = 1000;
  private static final int VIBRATION_MS = 200;

  private ToneGenerator toneGen;
  private int currentVolume = 100; // Default volume (0-100)
  private Context context;

  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    context = flutterPluginBinding.getApplicationContext();
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_beep");
    channel.setMethodCallHandler(this);
    toneGen = new ToneGenerator(AudioManager.STREAM_SYSTEM, currentVolume);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("playSysSound")) {
      int soundId = call.argument("soundId");
      Integer duration = call.argument("duration");
      Integer volume = call.argument("volume");
      Boolean vibrate = call.argument("vibrate");

      if (volume != null && volume != currentVolume) {
        setVolume(volume);
      }
      playSysSound(soundId, duration != null && duration > 0 ? duration : DEFAULT_DURATION_MS);
      if (Boolean.TRUE.equals(vibrate)) {
        vibrate();
      }
      result.success(true);
    } else if (call.method.equals("stopSysSound")) {
      stopSysSound();
      result.success(true);
    } else if (call.method.equals("setVolume")) {
      int volume = call.argument("volume");
      setVolume(volume);
      result.success(true);
    } else if (call.method.equals("vibrate")) {
      vibrate();
      result.success(true);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
    if (toneGen != null) {
      toneGen.release();
      toneGen = null;
    }
  }

  private void playSysSound(int soundID, int durationMs) {
    if (toneGen != null) {
      toneGen.startTone(soundID, durationMs);
    }
  }

  private void stopSysSound() {
    if (toneGen != null) {
      toneGen.stopTone();
    }
  }

  private void setVolume(int volume) {
    // Ensure volume is between 0 and 100
    currentVolume = Math.max(0, Math.min(100, volume));

    // Recreate ToneGenerator with new volume
    if (toneGen != null) {
      toneGen.release();
    }
    toneGen = new ToneGenerator(AudioManager.STREAM_SYSTEM, currentVolume);
  }

  private void vibrate() {
    Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    if (vibrator == null || !vibrator.hasVibrator()) {
      return;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      vibrator.vibrate(VibrationEffect.createOneShot(VIBRATION_MS, VibrationEffect.DEFAULT_AMPLITUDE));
    } else {
      vibrator.vibrate(VIBRATION_MS);
    }
  }
}
