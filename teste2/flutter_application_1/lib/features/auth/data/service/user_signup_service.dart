import 'dart:convert';
import 'package:dio/dio.dart';

class UserSignupService {
  final Dio _dio;

  UserSignupService(this._dio);

  Future<List<Aluno>> getAllAlunos() async {
    try {
      final response = await _dio.get('http://10.0.2.2:8080/v1/student/list');
      final List<dynamic> data = response.data;
      return data.map((item) => Aluno.fromMap(item)).toList();
    } catch (e) {
      print('Erro ao obter alunos: $e');
      throw Exception('Erro ao obter alunos');
    }
  }

  Future<int> signupUser({required Aluno alunoDTO}) async {
    try {
      final response = await _dio.post(
        'http://10.0.2.2:8080/v1/student/register',
        data: alunoDTO.toMap(),
        options: Options(
          responseType: ResponseType.json,
          headers: {
            'Content-Type': 'application/json',
          },
        ),
      );

      if (response.statusCode == 200) {
        print('Cadastro realizado com sucesso');
      } else {
        print('Erro ao cadastrar aluno: ${response.statusCode}');
      }

      return response.statusCode ?? 404;
    } catch (e) {
      print('Erro durante a requisição: $e');
      return 404; // Ou qualquer tratamento específico desejado para erro de requisição
    }
  }
}

class Aluno {
  final String id;
  final String name;
  final String email;
  final String role;
  final String lastName;
  final dynamic course;
  final List<dynamic> enrollments;
  final dynamic address;
  final int quantityEnrollments;
  final bool enrollmentsEmpty;
  final String password;

  Aluno({
    required this.id,
    required this.name,
    required this.email,
    required this.role,
    required this.lastName,
    required this.course,
    required this.enrollments,
    required this.address,
    required this.quantityEnrollments,
    required this.enrollmentsEmpty,
    required this.password,
  });

  factory Aluno.fromMap(Map<String, dynamic> map) {
    return Aluno(
      id: map['id'] as String,
      name: map['name'] as String,
      email: map['email'] as String,
      role: map['role'] as String,
      lastName: map['lastName'] as String,
      course: map['course'],
      enrollments: List<dynamic>.from(map['enrollments'] ?? []),
      address: map['address'],
      quantityEnrollments: map['quantityEnrollments'] ?? 0,
      enrollmentsEmpty: map['enrollmentsEmpty'] ?? false,
      password: map['password'] as String,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id': id,
      'name': name,
      'email': email,
      'role': role,
      'lastName': lastName,
      'course': course,
      'enrollments': enrollments,
      'address': address,
      'quantityEnrollments': quantityEnrollments,
      'enrollmentsEmpty': enrollmentsEmpty,
      'password': password, // Adicionar ao mapa
    };
  }

  String toJson() => json.encode(toMap());

  factory Aluno.fromJson(String source) => Aluno.fromMap(json.decode(source));

  @override
  String toString() {
    return 'Aluno(id: $id, name: $name, email: $email, role: $role, lastName: $lastName, '
        'course: $course, enrollments: $enrollments, address: $address, '
        'quantityEnrollments: $quantityEnrollments, enrollmentsEmpty: $enrollmentsEmpty)';
  }
}
