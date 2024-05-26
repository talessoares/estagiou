import 'package:flutter/material.dart';
import 'package:flutter_application_1/features/auth/auth_module.dart';
import 'package:flutter_application_1/features/auth/view/auth_page.dart';
import 'package:flutter_application_1/shared/module/core_module.dart';
import 'package:flutter_modular/flutter_modular.dart';

void main() {
  return runApp(ModularApp(module: AppModule(), child: const MainApp()));
}

class AppModule extends Module {
  @override
  void binds(i) {}
  @override
  void exportedBinds(i) {}

  @override
  List<Module> get imports => [AuthModule(), CoreModule()];

  @override
  void routes(r) {
    r.child('/', child: (context) => const AuthPage());
    r.module('/auth', module: AuthModule());
  }
}

class MainApp extends StatelessWidget {
  const MainApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: Modular.routerConfig,
      theme: ThemeData(
          fontFamily: 'Poppins',
          elevatedButtonTheme: ElevatedButtonThemeData(
            style: ElevatedButton.styleFrom(
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(50),
              ),
              minimumSize: const Size(double.infinity, 50),
              backgroundColor: const Color(0xFF23A331),
            ),
          )),
    );
  }
}
