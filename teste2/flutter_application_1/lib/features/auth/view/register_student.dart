import 'package:flutter/material.dart';
import 'package:flutter_application_1/extensions/custom_padding.dart';
import 'package:flutter_application_1/features/auth/view/auth_student.dart';
import 'package:flutter_application_1/features/auth/view/components/login_text_field.dart';
import 'package:google_sign_in/google_sign_in.dart';

class RegisterStudent extends StatefulWidget {
  const RegisterStudent({Key? key}) : super(key: key);

  @override
  State<RegisterStudent> createState() => _RegisterStudentState();
}

const List<String> scopes = <String>[
  'email',
  'https://www.googleapis.com/auth/contacts.readonly',
];
GoogleSignIn _googleSignIn = GoogleSignIn(
  scopes: scopes,
);

class _RegisterStudentState extends State<RegisterStudent> {
  final _formKey = GlobalKey<FormState>();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _firstNameController = TextEditingController();
  final TextEditingController _lastNameController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    _firstNameController.dispose();
    _lastNameController.dispose();
    super.dispose();
  }

  void _register() {
    if (_formKey.currentState!.validate()) {
      // Coloque aqui a lógica de autenticação
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: BackButton(
          onPressed: () => Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const AuthStudent()),
          ),
        ),
        toolbarHeight: 65,
        title: const Text(
          'Estudante',
          style: TextStyle(
            fontFamily: 'Poppins',
            fontSize: 20,
            color: Colors.white,
            fontWeight: FontWeight.w600,
          ),
        ),
        backgroundColor: const Color(0xFF23A331),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.fromLTRB(28, 10, 28, 0),
          color: const Color(0xFFFBF6FF),
          child: Form(
            key: _formKey,
            child: Column(
              mainAxisSize: MainAxisSize.min,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 10),
                const Text(
                  'Cadastro',
                  style: TextStyle(
                    fontSize: 22,
                    color: Color(0xFF1A7924),
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(height: 10),
                Text(
                  'Realize o cadastro da sua conta:',
                  style: Theme.of(context)
                      .textTheme
                      .bodyMedium!
                      .copyWith(fontSize: 16),
                ),
                50.ph,
                SizedBox(
                  height: 75,
                  child: LoginTextField(
                    labelText: 'Nome',
                    controller: _firstNameController,
                    isPassword: false,
                  ),
                ),
                1.ph,
                SizedBox(
                  height: 75,
                  child: LoginTextField(
                    labelText: 'Sobrenome',
                    controller: _lastNameController,
                    isPassword: false,
                  ),
                ),
                1.ph,
                SizedBox(
                  height: 75,
                  child: LoginTextField(
                    labelText: 'Email',
                    controller: _emailController,
                    isPassword: false,
                  ),
                ),
                1.ph,
                SizedBox(
                  height: 75,
                  child: LoginTextField(
                    labelText: 'Senha',
                    controller: _passwordController,
                    isPassword: true,
                  ),
                ),
                1.ph,
                SizedBox(
                  height: 60,
                  child: ElevatedButton(
                    onPressed: _register,
                    style: ElevatedButton.styleFrom(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(50),
                      ),
                      minimumSize: const Size(double.infinity, 50),
                      backgroundColor: const Color(0xFF23A331),
                    ),
                    child: const Center(
                      child: Text(
                        'CADASTRAR',
                        style: TextStyle(
                          color: Colors.white,
                          fontFamily: 'Poppins',
                          fontWeight: FontWeight.w600,
                          fontSize: 16,
                        ),
                      ),
                    ),
                  ),
                ),
                10.ph,
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    GestureDetector(
                      onTap: () {
                        Navigator.pushReplacement(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const AuthStudent()),
                        );
                      },
                      child: Text(
                        'Já possui conta? Faça login',
                        style: Theme.of(context).textTheme.bodyMedium!.copyWith(
                            fontSize: 16, color: const Color(0xFF1A7924)),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
