import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:wallpaper_manager/wallpaper_manager.dart';
import 'package:fluttertoast/fluttertoast.dart';

import '../providers/image_provider.dart';

class Option extends StatelessWidget {
  final String url;
  Option(this.url);
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        Tile('Home Screen', WallpaperManager.HOME_SCREEN, Icons.home, context),
        Divider(
          thickness: 1,
        ),
        Tile('Lock Screen', WallpaperManager.LOCK_SCREEN,
            Icons.screen_lock_portrait, context),
        Divider(
          thickness: 1,
        ),
        Tile('Both Screen', WallpaperManager.BOTH_SCREENS,
            Icons.mobile_friendly, context),
      ],
    );
  }

  Widget Tile(String name, int location, IconData icon, BuildContext context) {
    return ListTile(
      title: Text(name),
      leading: Icon(
        icon,
        color: Colors.white,
      ),
      onTap: () async {
        String msg = await Provider.of<Images>(context, listen: false)
            .setWallpaper(url, location);
        Navigator.of(context).pop();
        Fluttertoast.showToast(
          msg: msg,
          backgroundColor: Colors.green[100],
          fontSize: 16,
          gravity: ToastGravity.CENTER,
          toastLength: Toast.LENGTH_LONG,
        );
      },
    );
  }
}
