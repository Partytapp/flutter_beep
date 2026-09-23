import AudioToolbox
import Flutter
import UIKit

public class FlutterBeepPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_beep", binaryMessenger: registrar.messenger())
    registrar.addMethodCallDelegate(FlutterBeepPlugin(), channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "playSysSound":
      let args = call.arguments as? [String: Any] ?? [:]
      let soundId = SystemSoundID(args["soundId"] as? Int ?? 0)
      // System sounds have no volume API on iOS; the device volume applies.
      if args["vibrate"] as? Bool == true {
        AudioServicesPlayAlertSound(soundId)
      } else {
        AudioServicesPlaySystemSound(soundId)
      }
      result(nil)
    case "vibrate":
      AudioServicesPlaySystemSound(kSystemSoundID_Vibrate)
      result(nil)
    default:
      result(FlutterMethodNotImplemented)
    }
  }
}
