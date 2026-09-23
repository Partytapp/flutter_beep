import Flutter
import UIKit
import XCTest

@testable import flutter_beep

class RunnerTests: XCTestCase {

  func testPlaySysSoundReturnsNil() {
    let plugin = FlutterBeepPlugin()
    let call = FlutterMethodCall(methodName: "playSysSound", arguments: ["soundId": 1000])
    let resultExpectation = expectation(description: "result block must be called.")
    plugin.handle(call) { result in
      XCTAssertNil(result)
      resultExpectation.fulfill()
    }
    waitForExpectations(timeout: 1)
  }

  func testUnknownMethodIsNotImplemented() {
    let plugin = FlutterBeepPlugin()
    let call = FlutterMethodCall(methodName: "nope", arguments: nil)
    let resultExpectation = expectation(description: "result block must be called.")
    plugin.handle(call) { result in
      XCTAssertTrue((result as AnyObject) === FlutterMethodNotImplemented)
      resultExpectation.fulfill()
    }
    waitForExpectations(timeout: 1)
  }
}
