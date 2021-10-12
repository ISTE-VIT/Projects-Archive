import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/image_provider.dart';
import '../providers/image_model.dart';
import '../screens/imageScreen.dart';
import '../widgets/imageCard.dart';

class SearchResultScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    List<ImageModel> images = Provider.of<Images>(context).images;
    return Scaffold(
      appBar: AppBar(
        title: Text('Search Results'),
        centerTitle: true,
      ),
      body: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2, childAspectRatio: 9 / 16),
        itemCount: images.length,
        itemBuilder: (context, index) {
          return InkWell(
            onTap: () {
              return Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => ImageScreen(
                    url: images[index].url,
                    title: images[index].user,
                    description: '',
                  ),
                ),
              );
            },
            child: Hero(
              tag: images[index].url,
              child: ImageCard(
                url: images[index].url,
                title: images[index].user,
                description: ' ',
              ),
            ),
          );
        },
      ),
    );
  }
}
