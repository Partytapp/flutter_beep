import 'package:flutter_test/flutter_test.dart';

import 'package:flutter_beep_example/main.dart';

void main() {
  testWidgets('renders demo page', (WidgetTester tester) async {
    await tester.pumpWidget(const MyApp());

    expect(find.text('Flutter Beep Plugin Demo'), findsOneWidget);
    expect(find.text('Beep Success'), findsOneWidget);
  });
}
