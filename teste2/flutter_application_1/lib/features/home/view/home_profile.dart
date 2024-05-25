import 'package:flutter/material.dart';
import 'package:flutter_application_1/theme/palette.dart';
import 'package:flutter_application_1/features/home/view/home_page.dart';

class HomeProfile extends StatefulWidget {
  const HomeProfile({Key? key}) : super(key: key);

  @override
  // ignore: library_private_types_in_public_api
  _HomeProfileState createState() => _HomeProfileState();
}

class _HomeProfileState extends State<HomeProfile> {
  int _selectedIndex = 2; // Índice inicial do item selecionado

  // Função chamada ao selecionar um item da barra de navegação
  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
      if (_selectedIndex == 0) {
        // Se o índice for 0 (Menu), navegue para HomePage
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(builder: (context) => const HomePage()),
        );
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // Scaffold com AppBar
          Scaffold(
            appBar: AppBar(
              backgroundColor: Palette.lightGreen,
            ),
          ),
          Positioned(
            top: 90.0, // Ajuste a posição conforme necessário
            left: 0,
            right: 0,
            child: Center(
              child: Container(
                width: 90.0, // Tamanho do fundo redondo
                height: 90.0, // Tamanho do fundo redondo
                decoration: BoxDecoration(
                  color: Colors.grey[200], // Cor do fundo
                  shape: BoxShape.circle, // Forma circular
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.2), // Cor da sombra
                      spreadRadius: 2, // Propagação da sombra
                      blurRadius: 5, // Desfoque da sombra
                      offset: const Offset(0, 3), // Posição da sombra
                    ),
                  ],
                ),
                child: const Center(
                  child: Icon(
                    Icons.person,
                    size: 70.0, // Ajuste o tamanho do ícone conforme necessário
                    color: Palette
                        .lightGreen, // Ajuste a cor do ícone conforme necessário
                  ),
                ),
              ),
            ),
          ),
          // Exibição do nome de usuário
          const Positioned(
            top: 200.0, // Ajuste a posição conforme necessário
            left: 0,
            right: 0,
            child: Center(
              child: Text(
                'Nome do usuário',
                style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
              ),
            ),
          ),
          // Exibição da descrição
          const Positioned(
            top: 240.0, // Ajuste a posição conforme necessário
            left: 0,
            right: 0,
            child: Center(
              child: Text(
                'Curso do Aluno',
                textAlign: TextAlign.center,
                style: TextStyle(fontSize: 16.0),
              ),
            ),
          ),
          // Ícones do telefone e da carta
          Positioned(
            top: 300.0, // Ajuste a posição conforme necessário
            left: 0,
            right: 0,
            child: Center(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    width: 60.0, // Tamanho do fundo redondo
                    height: 60.0, // Tamanho do fundo redondo
                    decoration: BoxDecoration(
                      color: Colors.grey[200], // Cor do fundo
                      shape: BoxShape.circle, // Forma circular
                      border: Border.all(
                        color: Palette.lightGreen, // Cor do contorno
                        width: 1.0, // Largura do contorno
                      ),
                    ),
                    child: const Center(
                      child: Icon(
                        Icons.phone,
                        size:
                            30.0, // Ajuste o tamanho do ícone conforme necessário
                        color: Palette
                            .lightGreen, // Ajuste a cor do ícone conforme necessário
                      ),
                    ),
                  ),
                  const SizedBox(width: 20), // Espaçamento entre os ícones
                  Container(
                    width: 60.0, // Tamanho do fundo redondo
                    height: 60.0, // Tamanho do fundo redondo
                    decoration: BoxDecoration(
                      color: Colors.grey[200], // Cor do fundo
                      shape: BoxShape.circle, // Forma circular
                      border: Border.all(
                        color: Palette.lightGreen, // Cor do contorno
                        width: 1.0, // Largura do contorno
                      ),
                    ),
                    child: const Center(
                      child: Icon(
                        Icons.mail,
                        size:
                            30.0, // Ajuste o tamanho do ícone conforme necessário
                        color: Palette
                            .lightGreen, // Ajuste a cor do ícone conforme necessário
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home, color: Palette.lightGreen),
            label: 'Menu',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.article, color: Palette.lightGreen),
            label: 'Documentos',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person, color: Palette.lightGreen),
            label: 'Perfil',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Palette.lightGreen,
        onTap: _onItemTapped,
      ),
    );
  }
}
