import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';

class RegisterStudentGoogle extends StatefulWidget {
  const RegisterStudentGoogle({Key? key}) : super(key: key);

  @override
  State<RegisterStudentGoogle> createState() => RegisterStudentGoogleState();
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

class RegisterStudentGoogleState extends State<RegisterStudentGoogle> {
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
                'Cadastro',
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
              child: Text('Realize o cadastro com o Google:',
                  style: TextStyle(
                      fontSize: 16,
                      color: Color(0xFF585858),
                      fontFamily: 'Poppins',
                      fontWeight: FontWeight.w400)),
            ),
            const SizedBox(
              height: 30,
            ),
            Row(
              children: [
                Expanded(
                  child: Material(
                    elevation: 2,
                    borderRadius: BorderRadius.circular(50),
                    child: InkWell(
                      onTap: () => _googleSignIn.signIn(),
                      borderRadius: BorderRadius.circular(50),
                      splashColor: Color(0xFF23A331).withOpacity(1),
                      child: SizedBox(
                          width: 300,
                          child: Image.asset(
                            'assets/iconGoogle.png',
                            height: 70,
                          )),
                    ),
                  ),
                ),
              ],
            ),
            const Spacer(),
          ],
        ),
      ),
    );
  }
}
