import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

import '../screens/categoryScreen.dart';

class CategoryGrid extends StatelessWidget {
  final Function changeloading;
  CategoryGrid(this.changeloading);

  final List<Map<String, String>> category = [
    {
      "title": "Nature",
      "image":
          "https://cdn.pixabay.com/photo/2015/12/01/20/28/green-1072828_960_720.jpg"
    },
    {
      "title": "Science",
      "image":
          "https://cdn.pixabay.com/photo/2012/11/28/11/25/satellite-67718_960_720.jpg"
    },
    {
      "title": "Education",
      "image":
          "https://cdn.pixabay.com/photo/2015/11/19/21/10/knowledge-1052010_960_720.jpg"
    },
    {
      "title": "Feelings",
      "image":
          "https://cdn.pixabay.com/photo/2017/11/11/21/55/girl-2940655_960_720.jpg"
    },
    {
      "title": "Health",
      "image":
          "https://cdn.pixabay.com/photo/2018/02/06/14/07/dance-3134828_960_720.jpg"
    },
    {
      "title": "Music",
      "image":
          "https://cdn.pixabay.com/photo/2015/05/07/11/02/guitar-756326_960_720.jpg"
    },
    {
      "title": "Religion",
      "image":
          "https://cdn.pixabay.com/photo/2014/12/03/12/21/monk-555391_960_720.jpg"
    },
    {
      "title": "Places",
      "image":
          "https://cdn.pixabay.com/photo/2017/12/10/17/40/prague-3010407_960_720.jpg"
    },
    {
      "title": "Animals",
      "image":
          "https://cdn.pixabay.com/photo/2017/10/20/10/58/elephant-2870777_960_720.jpg"
    },
    {
      "title": "Industry",
      "image":
          "https://cdn.pixabay.com/photo/2014/02/05/08/19/smoke-258786_960_720.jpg"
    },
    {
      "title": "Computer",
      "image":
          "https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806_960_720.jpg"
    },
    {
      "title": "Food",
      "image":
          "https://cdn.pixabay.com/photo/2017/11/08/22/18/spaghetti-2931846_960_720.jpg"
    },
    {
      "title": "People",
      "image":
          "https://cdn.pixabay.com/photo/2015/06/22/08/40/child-817373_960_720.jpg"
    },
    {
      "title": "Sports",
      "image":
          "https://cdn.pixabay.com/photo/2017/01/16/15/28/boxer-1984344_960_720.jpg"
    },
    {
      "title": "Transportationn",
      "image":
          "https://cdn.pixabay.com/photo/2018/02/21/10/16/train-3169964_960_720.jpg"
    },
    {
      "title": "Travel",
      "image":
          "https://cdn.pixabay.com/photo/2017/06/05/11/01/airport-2373727_960_720.jpg"
    },
    {
      "title": "Buildings",
      "image":
          "https://cdn.pixabay.com/photo/2018/02/27/06/30/skyscraper-3184798_960_720.jpg"
    },
    {
      "title": "Business",
      "image":
          "https://cdn.pixabay.com/photo/2015/01/09/11/08/startup-594090_960_720.jpg"
    },
    {
      "title": "Fashion",
      "image":
          "https://cdn.pixabay.com/photo/2015/11/07/11/46/fashion-1031469_960_720.jpg"
    },
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      height: double.infinity,
      width: double.infinity,
      padding: const EdgeInsets.symmetric(horizontal: 4),
      child: GridView.builder(
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            childAspectRatio: 3 / 2,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10),
        itemBuilder: (context, index) {
          return InkWell(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) {
                    return CategorySceen(
                      url: category[index]['image'],
                      title: category[index]['title'],
                    );
                  },
                ),
              );
            },
            child: Card(
              clipBehavior: Clip.antiAlias,
              elevation: 8,
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0)),
              child: Stack(
                children: [
                  Container(
                    child: CachedNetworkImage(
                      imageUrl: category[index]['image'],
                      fit: BoxFit.cover,
                    ),
                    width: double.infinity,
                    height: double.infinity,
                  ),
                  Container(
                    decoration: BoxDecoration(
                      gradient: LinearGradient(
                        colors: [Colors.black38, Colors.transparent],
                        begin: Alignment.bottomCenter,
                        end: Alignment.topCenter,
                      ),
                    ),
                    child: Center(
                      child: Text(
                        category[index]['title'],
                        style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                            color: Colors.white),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          );
        },
        itemCount: category.length,
      ),
    );
  }
}
