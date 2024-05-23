import 'package:flutter/material.dart';
import 'package:flutter_application_1/extensions/custom_padding.dart';
import 'package:flutter_application_1/theme/palette.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Palette.lightGreen,
      ),
      body: ListView(
        children: [
          Align(
            alignment: Alignment.centerLeft,
            child: Container(
              height: size.height * 0.2,
              width: size.width * 0.45,
              margin: const EdgeInsets.only(top: 4, left: 4),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(25),
                  shape: BoxShape.rectangle,
                  gradient: LinearGradient(
                      colors: [Palette.darkGreen, Palette.lightGreen])),
              child: Stack(
                children: [
                  Positioned(
                    top: 12,
                    left: 12,
                    child: Column(
                      children: [
                        Text(
                          '29',
                          style: Theme.of(context)
                              .textTheme
                              .bodyMedium!
                              .copyWith(
                                  color: Palette.whiteColor,
                                  fontSize: 24,
                                  fontWeight: FontWeight.bold),
                        ),
                        Text(
                          'Inscrições',
                          style:
                              Theme.of(context).textTheme.bodyMedium!.copyWith(
                                    color: Palette.whiteColor,
                                    fontSize: 16,
                                  ),
                        ),
                      ],
                    ),
                  ),
                  Positioned(
                      bottom: -16,
                      right: -12,
                      child: Image.asset(
                        'assets/confirmed.png',
                        alignment: Alignment.bottomRight,
                      ))
                ],
              ),
            ),
          ),
          4.ph,
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 4.0),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text('Vagas recomendadas'),
                SizedBox(
                  width: 120,
                  height: 10,
                  child: ListView.separated(
                    separatorBuilder: (context, index) {
                      return 4.pw;
                    },
                    itemCount: 2,
                    scrollDirection: Axis.horizontal,
                    itemBuilder: (context, index) {
                      return Container(
                        width: 20,
                        height: 2,
                        decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(50),
                            color: Palette.darkGreen),
                      );
                    },
                  ),
                )
              ],
            ),
          ),
          SizedBox(
            width: double.infinity,
            height: size.height * 0.125,
            child: ListView.separated(
              separatorBuilder: (context, index) {
                return 4.pw;
              },
              scrollDirection: Axis.horizontal,
              itemCount: 2,
              itemBuilder: (context, index) {
                return Container(
                  padding: const EdgeInsets.all(4),
                  margin: const EdgeInsets.all(4),
                  height: size.height * 0.1,
                  decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(25),
                      color: Palette.whiteColor,
                      border: Border.all(width: 1, color: Palette.darkGreen),
                      boxShadow: [
                        BoxShadow(
                            color: Palette.darkGreen,
                            offset: const Offset(1, 1))
                      ]),
                  child: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Flexible(
                          child: Container(
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(25),
                                color: Palette.lightGreen,
                              ),
                              child: Image.asset('assets/logo.png'))),
                      8.pw,
                      Column(
                        children: [
                          4.ph,
                          Flexible(
                            child: Row(
                              children: [
                                const Icon(
                                  Icons.work,
                                ),
                                4.pw,
                                const Text('Engenharia de Software'),
                              ],
                            ),
                          ),
                          Flexible(
                            child: Row(
                              children: [
                                const Icon(
                                  Icons.pin_drop,
                                  color: Colors.red,
                                ),
                                4.pw,
                                const Text('Humaitá, Amazonas'),
                              ],
                            ),
                          ),
                          Flexible(
                            child: Row(
                              children: [
                                const Icon(
                                  Icons.payment,
                                ),
                                4.pw,
                                const Text('Pagamento em jacaré'),
                              ],
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Menu',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: 'Documentos',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Perfil',
          ),
        ],
      ),
    );
  }
}
