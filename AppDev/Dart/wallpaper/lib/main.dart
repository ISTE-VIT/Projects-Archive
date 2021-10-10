import 'package:Flutter_Wallpaper/screens/splashScreen.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import './providers/image_provider.dart';

void main() {
  debugDefaultTargetPlatformOverride = TargetPlatform.fuchsia;
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [ChangeNotifierProvider(create: (ctx) => Images())],
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
            brightness: Brightness.light,
            primarySwatch: Colors.purple,
            accentColor: Colors.amber,
            fontFamily: "Raleway",
            iconTheme: IconThemeData(color: Colors.purple),
            floatingActionButtonTheme: FloatingActionButtonThemeData(
              backgroundColor: Colors.amber,
              splashColor: Colors.blueAccent,
              foregroundColor: Colors.black,
            )),
        darkTheme: ThemeData(
          brightness: Brightness.dark,
          visualDensity: VisualDensity.adaptivePlatformDensity,
          fontFamily: "Raleway",
          iconTheme: IconThemeData(color: Colors.black),
          floatingActionButtonTheme: FloatingActionButtonThemeData(
              backgroundColor: Colors.amber,
              splashColor: Colors.blueAccent,
              foregroundColor: Colors.black),
        ),
        home: SplashScreen(),
      ),
    );
  }
}
