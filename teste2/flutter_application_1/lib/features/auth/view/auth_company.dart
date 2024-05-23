import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';

class AuthCompany extends StatefulWidget {
  const AuthCompany({super.key});

  @override
  State<AuthCompany> createState() => _AuthCompanyState();
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

class _AuthCompanyState extends State<AuthCompany> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: Container(
        padding: const EdgeInsets.all(8),
        child: Column(
          children: [
            Text('Login',
                style: Theme.of(context)
                    .textTheme
                    .bodyMedium!
                    .copyWith(fontSize: 14)),
            Text('Realize o login na sua conta',
                style: Theme.of(context)
                    .textTheme
                    .bodyMedium!
                    .copyWith(fontSize: 14)),
            const Spacer(),
            Row(
              children: [
                Text('Realizar login com o google',
                    style: Theme.of(context)
                        .textTheme
                        .bodyMedium!
                        .copyWith(fontSize: 14)),
              ],
            ),
            const SizedBox(
              height: 8,
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
