import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class ImageCard extends StatefulWidget {
  final String url;
  final String title;
  final String description;
  ImageCard({this.url, this.title, this.description});

  @override
  _ImageCardState createState() => _ImageCardState();
}

class _ImageCardState extends State<ImageCard> {
  BoxDecoration _customDecoration() {
    return const BoxDecoration(
        gradient: LinearGradient(
      colors: [Colors.black, Colors.transparent],
      begin: Alignment.bottomCenter,
      end: Alignment.topCenter,
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.antiAlias,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(4.0)),
      child: Stack(
        children: [
          Container(
            child: (widget.url != null)
                ? CachedNetworkImage(
                    imageUrl: widget.url,
                    fit: BoxFit.cover,
                    filterQuality: FilterQuality.high,
                  )
                : null,
            width: double.infinity,
            height: double.infinity,
          ),
          Positioned(
            bottom: 0,
            left: 0,
            right: 0,
            child: Container(
              height: 200,
              decoration: _customDecoration(),
            ),
          ),
          Positioned(
            left: 0,
            bottom: 0,
            right: 0,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisSize: MainAxisSize.min,
              children: [
                Text(
                  (widget.title != null) ? widget.title : '',
                  style: TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                      color: Colors.white70),
                  maxLines: 1,
                ),
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Text(
                    (widget.description != null) ? widget.description : '',
                    maxLines: 1,
                    style: TextStyle(fontSize: 16, color: Colors.white54),
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }
}
