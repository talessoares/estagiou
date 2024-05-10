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
      appBar: AppBar(),
      body: Container(
        padding: const EdgeInsets.all(8),
        child: Column(
          children: [
            const SizedBox(height: 10),
            Align(
              alignment: Alignment.centerLeft,
              child: Text(
                'Cadastro',
                style: TextStyle(
                  fontSize: 22,
                  color: Color(0xFF1A7924),
                ),
              ),
            ),
            const SizedBox(height: 10),
            Align(
              alignment: Alignment.centerLeft,
              child: Text(
                'Realize o cadastro com o Google:',
                style: Theme.of(context)
                    .textTheme
                    .bodyMedium!
                    .copyWith(fontSize: 14),
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            Row(
              children: [
                Expanded(
                  child: Material(
                    elevation: 2,
                    borderRadius: BorderRadius.circular(30),
                    child: InkWell(
                      onTap: () => _googleSignIn.signIn(),
                      borderRadius: BorderRadius.circular(20),
                      child: Container(
                          width: 300,
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(30),
                              border: Border.all(color: Colors.black)),
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
