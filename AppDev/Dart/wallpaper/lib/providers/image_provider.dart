import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:image_downloader/image_downloader.dart';
import 'package:wallpaper_manager/wallpaper_manager.dart';
import 'package:flutter_cache_manager/flutter_cache_manager.dart';

import './image_model.dart';

class Images with ChangeNotifier {
  List<ImageModel> mainImages = [];
  List<ImageModel> images = [];
  List<ImageModel> categoryImages = [];
  Stream<String> progress;

  Future<List<ImageModel>> imageSearch(String site) async {
    final response = await http.get('$site');
    final extractedData = jsonDecode(response.body) as Map<String, dynamic>;
    final extractedImage = extractedData['hits'];
    final List<ImageModel> loadedimage = [];
    extractedImage.forEach((imageData) {
      loadedimage.add(
        ImageModel(
          id: imageData['id'].toString(),
          user: imageData['user'],
          url: imageData['largeImageURL'],
          tags: imageData['tags'],
        ),
      );
    });
    return loadedimage;
  }

  Future<void> getImages() async {
    List<ImageModel> loadedimage = await imageSearch(
        'https://pixabay.com/api/?key=17844631-1a0191a2feabf070a0377cdc4&editors_choice=true&per_page=25&orientation=vertical&image_type=photo&safesearch=true&pretty=true');
    mainImages = loadedimage;
  }

  Future<void> searchImage(String s) async {
    s = s.replaceAll(" ", "+");
    List<ImageModel> loadedImage = await imageSearch(
        'https://pixabay.com/api/?key=17844631-1a0191a2feabf070a0377cdc4&q=$s&safesearch=true');
    images = loadedImage;
    notifyListeners();
  }

  void clearSearch() {
    images = [];
  }

  Future<List<ImageModel>> getCategory(String s) async {
    s = s.toLowerCase();
    List<ImageModel> loadedImage = await imageSearch(
        'https://pixabay.com/api/?key=17844631-1a0191a2feabf070a0377cdc4&category=$s&image_type=photo&safesearch=true');
    categoryImages = loadedImage;
    return categoryImages;
    // notifyListeners();
  }

  Future<bool> downloadImage(String url) async {
    try {
      var imageId = await ImageDownloader.downloadImage(url);
      if (imageId == null) {
        return false;
      }
      print(imageId);
      return true;
    } on PlatformException catch (error) {
      print(error);
      return false;
    }
  }

  Future<String> setWallpaper(String url, int location) async {
    print(location);
    var file = await DefaultCacheManager().getSingleFile(url);
    String result;
    try {
      result = await WallpaperManager.setWallpaperFromFile(file.path, location);
      print(result);
      return result;
    } on PlatformException {
      result = 'Failed to get Wallpaper';
      print(result);
      return result;
    }
  }
}
