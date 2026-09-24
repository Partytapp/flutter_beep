## 2.0.0
* Migrated to current Flutter tooling: Dart ^3.8.0, Flutter >=3.32.0, flutter_lints
* Android: Kotlin DSL build scripts, AGP 9.1.0, compileSdk 36, minSdk 24, Java 17
* iOS: plugin rewritten in Swift with Swift Package Manager support, minimum iOS 15.0
* Example app regenerated from the current Flutter template
* Fixed Android tones that never stopped: continuous tones (DTMF, dial, busy, ring, error...) now stop after 1 s unless `duration` is given
* Fixed `stopRecording()` being silent and `vibrate()` not vibrating on Android; `playSysSound(vibrate: true)` now vibrates on Android too
* Remapped `success()` and `error()` on iOS to the SIM toolkit ACK sounds and `warning()` to a short soft-error tone on Android and the USSD alert on iOS; `countdownSequence()` plays DTMF 3-2-1 on iOS
* `playSequence()` accepts a `duration`

## 1.1.0
* Upgraded Android dependencies
* Refactored methodchannel registration

## 1.0.0
Stable release

## 0.3.1
Support null safety

## 0.3.0
Support null safety

## 0.2.0
Official release

## 0.0.3
Update readme and changelog

## 0.0.2
Init version
