import 'package:flutter/foundation.dart';

class ImageModel {
  String id;
  String user;
  String tags;
  String url;
  ImageModel(
      {@required this.id,
      @required this.user,
      @required this.url,
      @required this.tags});
}
