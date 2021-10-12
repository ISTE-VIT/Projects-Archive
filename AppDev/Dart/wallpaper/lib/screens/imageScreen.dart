import 'dart:io';
import 'dart:async';

import 'package:path_provider/path_provider.dart';
import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_speed_dial/flutter_speed_dial.dart';
import 'package:provider/provider.dart';

import '../providers/image_provider.dart';
import '../widgets/options.dart';

class ImageScreen extends StatelessWidget {
  final String url;
  final String title;
  final String description;
  ImageScreen({this.url, this.title, this.description});

  @override
  Widget build(BuildContext context) {
    final _scaffoldKey = GlobalKey<ScaffoldState>();
    return Scaffold(
      key: _scaffoldKey,
      body: Container(
        child: Stack(
          children: [
            Hero(
              tag: url,
              child: Container(
                child: CachedNetworkImage(
                  imageUrl: url,
                  filterQuality: FilterQuality.high,
                ),
                height: double.infinity,
                width: double.infinity,
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: buildFAB(context, _scaffoldKey),
    );
  }

  SpeedDial buildFAB(
      BuildContext context, GlobalKey<ScaffoldState> _scaffoldKey) {
    return SpeedDial(
      animatedIcon: AnimatedIcons.menu_close,
      animatedIconTheme: IconThemeData(size: 20),
      curve: Curves.easeInCirc,
      children: [
        SpeedDialChild(
          backgroundColor: Colors.amber,
          child: Icon(
            Icons.download_rounded,
          ),
          onTap: () async {
            var result = await Provider.of<Images>(context, listen: false)
                .downloadImage(url);
            String msg =
                result ? 'Image Downloaded!!!' : 'Something Went Wrong!';
            _scaffoldKey.currentState
                .showSnackBar(SnackBar(content: Text(msg)));
          },
          label: 'Download',
          labelBackgroundColor: Colors.amber,
        ),
        SpeedDialChild(
          backgroundColor: Colors.amber,
          child: Icon(
            Icons.wallpaper,
          ),
          onTap: () {
            print('Set Wallpaper');
            showDialog(
              context: context,
              builder: (context) => Dialog(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(4)),
                child: Container(
                  height: 203,
                  child: Option(url),
                ),
              ),
            );
          },
          label: 'Set Wallpaper',
          labelBackgroundColor: Colors.amber,
        ),
      ],
    );
  }
}
