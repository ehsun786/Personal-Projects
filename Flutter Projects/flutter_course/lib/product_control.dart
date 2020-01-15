import "package:flutter/material.dart";

import './products.dart';
import 'package:flutter_course/product_control.dart';

class ProductControl extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return RaisedButton(
          color: Theme.of(context).primaryColor,
          onPressed: () {
            
          },
          child: Text('Add Product'),
        );
  }
}