import 'package:dio/dio.dart';
import 'package:flutter_modular/flutter_modular.dart';

class CoreModule extends Module {
  @override
  void exportedBinds(Injector i) {
    i.add(() => Dio(BaseOptions(
          validateStatus: (int? status) {
            return status != null;
          },
          contentType: Headers.jsonContentType,
        )));
  }
}
