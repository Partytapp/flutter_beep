# flutter_beep Example

Demonstrates how to use the flutter_beep plugin.
```dart
import 'package:flutter_beep/flutter_beep.dart';

ElevatedButton( child: Text("Beep Success"), onPressed: ()=> FlutterBeep.beep()),
ElevatedButton( child: Text("Beep Fail"), onPressed: ()=> FlutterBeep.beep(false)),
ElevatedButton( child: Text("Beep Android Custom"), onPressed: ()=> FlutterBeep.playSysSound(AndroidSoundIDs.TONE_CDMA_ABBR_ALERT)),
ElevatedButton( child: Text("Beep somthing"), onPressed: ()=> FlutterBeep.playSysSound(41)),
ElevatedButton( child: Text("Beep iOS Custom"), onPressed: ()=> FlutterBeep.playSysSound(iOSSoundIDs.AudioToneBusy)),
```

FREE