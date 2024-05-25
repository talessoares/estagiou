import 'package:flutter/material.dart';

class LoginTextField extends StatefulWidget {
  const LoginTextField(
      {super.key,
      required this.labelText,
      required this.controller,
      required this.isPassword});

  final String labelText;
  final TextEditingController controller;
  final bool isPassword;

  @override
  State<LoginTextField> createState() => _LoginTextFieldState();
}

class _LoginTextFieldState extends State<LoginTextField> {
  bool _obscureText = true;

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: widget.controller,
      obscureText: widget.isPassword,
      onTapOutside: (event) => FocusScope.of(context).unfocus(),
      decoration: InputDecoration(
        labelText: widget.labelText,
        labelStyle: const TextStyle(
          color: Color(0x66323232),
          fontFamily: 'Poppins',
          fontWeight: FontWeight.w500,
          fontSize: 16,
        ),
        contentPadding: const EdgeInsetsDirectional.fromSTEB(24, 24, 12, 16),
        enabledBorder: OutlineInputBorder(
          borderSide: const BorderSide(
            color: Color(0xFF1A7924),
          ),
          borderRadius: BorderRadius.circular(50),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: const BorderSide(
            color: Color(0xFF1A7924),
          ),
          borderRadius: BorderRadius.circular(20),
        ),
        suffixIcon: widget.isPassword
            ? IconButton(
                padding: const EdgeInsetsDirectional.symmetric(
                    vertical: 0, horizontal: 20),
                onPressed: () {
                  setState(() {
                    _obscureText = !_obscureText;
                  });
                },
                icon: Icon(
                  _obscureText ? Icons.visibility : Icons.visibility_off,
                  color: const Color(0XFF1A7924),
                ),
              )
            : null,
        fillColor: Colors.white,
        filled: true,
      ),
    );
  }
}
