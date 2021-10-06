import 'package:http/http.dart';
import 'dart:convert';
import 'package:intl/intl.dart';

class Worldtime {
  late String location; // location name for the UI
  late String time; // time in that location
  late String flag; // url to an asset flag icon
  late String url; // location url for api endpoint
  late bool isdaytime; //true or false depending on the time

  Worldtime({required this.location, required this.flag, required this.url});

  Future<void> getTime() async {
    try {
      Response response = await get(
          Uri.parse('https://www.worldtimeapi.org/api/timezone/$url'));
      Map data = jsonDecode(response.body);
      //print(data);
      // get properties from data
      String datetime = data['datetime'];
      String offset = data['utc_offset'].substring(0, 3);
      //print(datetime);
      //print(offset);

      //create a date time object
      DateTime now = DateTime.parse(datetime);
      now = now.add(Duration(hours: int.parse(offset)));

      //set time property
      isdaytime = now.hour > 6 && now.hour < 20 ? true : false;
      time = DateFormat.jm().format(now);
    } catch (e) {
      print(e);
      time = 'could not load time';
    }
  }
}
