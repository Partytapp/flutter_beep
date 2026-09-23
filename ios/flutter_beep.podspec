#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint flutter_beep.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'flutter_beep'
  s.version          = '2.0.0'
  s.summary          = 'A very lite module to play system sounds and beep for flutter apps (no sound files).'
  s.description      = <<-DESC
A very lite module to play system sounds and beep for flutter apps (no sound files).
                       DESC
  s.homepage         = 'https://github.com/trietho/flutter_beep'
  s.license          = { :file => '../LICENSE' }
  s.author           = 'GONoter'
  s.source           = { :path => '.' }
  s.source_files = 'flutter_beep/Sources/flutter_beep/**/*.swift'
  s.dependency 'Flutter'
  s.platform = :ios, '15.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
