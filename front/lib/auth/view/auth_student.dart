import 'package:estagio_u/auth/view/register_student_google.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';

class AuthStudent extends StatefulWidget {
  const AuthStudent({Key? key}) : super(key: key);

  @override
  State<AuthStudent> createState() => _AuthStudentState();
}

const List<String> scopes = <String>[
  'email',
  'https://www.googleapis.com/auth/contacts.readonly',
];
GoogleSignIn _googleSignIn = GoogleSignIn(
  // Optional clientId
  // clientId: 'your-client_id.apps.googleusercontent.com',
  scopes: scopes,
);

class _AuthStudentState extends State<AuthStudent> {
  TextEditingController _emailController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  bool _obscureText = true;

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          toolbarHeight: 65,
          title: const Text('Estudante',
              style: TextStyle(
                  fontFamily: 'Poppins',
                  fontSize: 20,
                  color: Colors.white,
                  fontWeight: FontWeight.w600)),
          flexibleSpace: Container(
            decoration: const BoxDecoration(
              gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: <Color>[Colors.black, Color(0xFF23A331)]),
            ),
          )),
      body: Container(
        padding: const EdgeInsets.fromLTRB(28, 10, 28, 0),
        color: const Color(0xFFFBF6FF),
        child: Column(
          children: [
            const SizedBox(height: 10),
            const Align(
              alignment: Alignment.centerLeft,
              child: Text(
                'Login',
                style: TextStyle(
                    fontSize: 22,
                    color: Color(0xFF1A7924),
                    fontFamily: 'Poppins',
                    fontWeight: FontWeight.w500),
              ),
            ),
            const SizedBox(height: 8),
            const Align(
              alignment: Alignment.centerLeft,
              child: Text('Realize o login na sua conta:',
                  style: TextStyle(
                      fontSize: 16,
                      color: Color(0xFF585858),
                      fontFamily: 'Poppins',
                      fontWeight: FontWeight.w400)),
            ),
            const SizedBox(height: 40),
            Row(
              children: [
                Expanded(
                  child: Column(
                    children: [
                      TextField(
                        controller: _emailController,
                        decoration: InputDecoration(
                            labelText: 'Email',
                            labelStyle: const TextStyle(
                              color: Color(0x66323232),
                              fontFamily: 'Poppins',
                              fontWeight: FontWeight.w500,
                              fontSize: 16,
                            ),
                            contentPadding:
                                const EdgeInsetsDirectional.fromSTEB(
                                    24, 24, 24, 16),
                            enabledBorder: OutlineInputBorder(
                              borderSide:
                                  const BorderSide(color: Color(0xFF1A7924)),
                              borderRadius: BorderRadius.circular(50),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide:
                                  const BorderSide(color: Color(0xFF1A7924)),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            fillColor: Colors.white,
                            filled: true),
                      ),
                      const SizedBox(height: 20),
                      TextField(
                        controller: _passwordController,
                        obscureText: _obscureText,
                        decoration: InputDecoration(
                            labelText: 'Senha',
                            labelStyle: const TextStyle(
                              color: Color(0x66323232),
                              fontFamily: 'Poppins',
                              fontWeight: FontWeight.w500,
                              fontSize: 16,
                            ),
                            contentPadding:
                                const EdgeInsetsDirectional.fromSTEB(
                                    24, 24, 12, 16),
                            enabledBorder: OutlineInputBorder(
                              borderSide:
                                  const BorderSide(color: Color(0xFF1A7924)),
                              borderRadius: BorderRadius.circular(50),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: BorderSide(color: Color(0xFF1A7924)),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            suffixIcon: IconButton(
                              padding: EdgeInsetsDirectional.symmetric(vertical: 0, horizontal: 20),
                              onPressed: () {
                                setState(() {
                                  _obscureText = !_obscureText;
                                });
                              },
                              icon: Icon(
                                _obscureText
                                    ? Icons.visibility
                                    : Icons.visibility_off,
                                color: Color(0XFF1A7924),
                              ),
                            ),
                            fillColor: Colors.white,
                            filled: true),
                      ),
                      const SizedBox(height: 30),
                      ElevatedButton(
                        onPressed: () {
                          // Coloque aqui a lógica de autenticação
                        },
                        style: ElevatedButton.styleFrom(
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(50),
                          ),
                          minimumSize: Size(double.infinity, 50),
                          backgroundColor: Color(0xFF23A331),
                        ),
                        child: const SizedBox(
                          height: 65,
                          child: Center(
                            child: Text(
                              'LOGIN',
                              style: TextStyle(
                                  color: Colors.white,
                                  fontFamily: 'Poppins',
                                  fontWeight: FontWeight.w600,
                                  fontSize: 16),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
            const SizedBox(height: 15),
            GestureDetector(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => RegisterStudentGoogle()),
                );
              },
              child: const Text(
                'Não possui conta? Cadastre-se',
                style: TextStyle(
                    fontSize: 16,
                    color: Color(0xFF585858),
                    fontFamily: 'Poppins',
                    fontWeight: FontWeight.w400),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
