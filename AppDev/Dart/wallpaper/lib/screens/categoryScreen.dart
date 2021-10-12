import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/image_model.dart';
import '../providers/image_provider.dart';
import '../screens/imageScreen.dart';
import '../widgets/imageCard.dart';

class CategorySceen extends StatefulWidget {
  final String url;
  final String title;
  CategorySceen({this.url, this.title});

  @override
  _CategorySceenState createState() => _CategorySceenState();
}

class _CategorySceenState extends State<CategorySceen> {
  bool isLoading = true;
  List<ImageModel> images;

  _getCategory() async {
    images = await Provider.of<Images>(context, listen: false)
        .getCategory(widget.title);
    setState(() {
      isLoading = false;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _getCategory();
  }

  @override
  Widget build(BuildContext context) {
    // List<ImageModel> images = Provider.of<Images>(context).categoryImages;
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        centerTitle: true,
      ),
      body: isLoading
          ? Center(
              child: CircularProgressIndicator(),
            )
          : GridView.builder(
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
