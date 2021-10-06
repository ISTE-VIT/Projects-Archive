import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  Map data = {};

  @override
  Widget build(BuildContext context) {
    data = data.isNotEmpty
        ? data
        : ModalRoute.of(context)!.settings.arguments as Map;

    // set background
    late Color touse;
    String bgimage = data['isdaytime'] ? 'day.jpg' : 'night.jpg';
    if (data['isdaytime'] == true)
      touse = Colors.black;
    else
      touse = Colors.white;

    return Scaffold(
        backgroundColor: Colors.indigo,
        body: SafeArea(
            child: Container(
          decoration: BoxDecoration(
              image: DecorationImage(
                  image: AssetImage('assets/$bgimage'), fit: BoxFit.cover)),
          child: Padding(
            padding: const EdgeInsets.fromLTRB(0, 30, 0, 0),
            child: Column(
              children: <Widget>[
                SizedBox(
                  height: 20.0,
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text(
                      data['location'],
                      style: TextStyle(
                          fontSize: 28.0, letterSpacing: 2.0, color: touse),
                    )
                  ],
                ),
                SizedBox(
                  height: 20.0,
                ),
                Text(
                  data['time'],
                  style: TextStyle(fontSize: 66.0, color: touse),
                ),
                SizedBox(
                  height: 550.0,
                ),
                FlatButton.icon(
                  onPressed: () async {
                    dynamic result =
                        await Navigator.pushNamed(context, '/location');
                    setState(() {
                      data = {
                        'time': result['time'],
                        'location': result['location'],
                        'flag': result['flag'],
                        'isdaytime': result['isdaytime'],
                      };
                    });
                  },
                  icon: Icon(
                    Icons.edit_location,
                    color: touse,
                  ),
                  label: Text(
                    'Edit Location',
                    style: TextStyle(color: touse),
                  ),
                ),
              ],
            ),
          ),
        )));
  }
}
