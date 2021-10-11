import 'package:Flutter_Wallpaper/screens/searchResultScreen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../providers/image_provider.dart';
import '../widgets/categoryGrid.dart';

class SearchScreen extends StatefulWidget {
  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  @override
  void initState() {
    super.initState();
    Provider.of<Images>(context, listen: false).clearSearch();
  }

  bool isLoading = false;

  changeLoading(bool val) {
    setState(() {
      isLoading = val;
    });
  }

  @override
  Widget build(BuildContext context) {
    print(isLoading);
    return Scaffold(
      appBar: AppBar(
        title: Text(
          'Search',
        ),
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(60),
          child: Container(
            margin: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
            decoration: BoxDecoration(
                color: Colors.white, borderRadius: BorderRadius.circular(8.0)),
            padding: const EdgeInsets.symmetric(horizontal: 10.0),
            child: TextFormField(
              style: TextStyle(
                  fontSize: 20, color: Theme.of(context).primaryColor),
              onFieldSubmitted: (value) async {
                setState(() {
                  isLoading = true;
                });
                await Provider.of<Images>(context, listen: false)
                    .searchImage(value);
                Navigator.of(context).push(
                  MaterialPageRoute(
                    builder: (context) => SearchResultScreen(),
                  ),
                );
                setState(() {
                  isLoading = false;
                });
              },
              decoration: InputDecoration(
                prefixIcon: Icon(
                  Icons.search,
                  color: Theme.of(context).iconTheme.color,
                ),
              ),
              cursorColor: Colors.black,
            ),
          ),
        ),
      ),
      body: Container(
        height: double.infinity,
        child: isLoading
            ? Center(
                child: CircularProgressIndicator(),
              )
            : CategoryGrid(changeLoading),
      ),
    );
  }
}
