import 'dart:async';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/image_provider.dart';
import '../screens/homepage.dart';

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  loadImages() async {
    await Provider.of<Images>(context, listen: false).getImages();
    Timer(
      Duration(seconds: 2),
      () => Navigator.of(context).pushReplacement(
        MaterialPageRoute(
          builder: (context) => HomepageScreen(),
        ),
      ),
    );
  }

  @override
  void initState() {
    // TODO: implement initState
    loadImages();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        height: double.infinity,
        width: double.infinity,
        child: Stack(
          children: [
            Center(
              child: Image.asset("assests/images/Infinity-3.3s-200px (2).gif"),
            ),
            Positioned(
              child: Container(
                width: MediaQuery.of(context).size.width,
                child: Text(
                  'By - SoulEater',
                  style: TextStyle(color: Colors.white24),
                  textAlign: TextAlign.center,
                ),
              ),
              bottom: 20,
            ),
          ],
        ),
      ),
    );
  }
}
