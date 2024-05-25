// ignore_for_file: public_member_api_docs, sort_constructors_first
import 'dart:convert';

import 'package:dio/dio.dart';

class ListarAlunosService {
  final Dio dio = Dio();

  Future<List<Aluno>> getAllAlunos() async {
    final response = await dio.get('http://10.0.2.2:8080/v1/student/list');
    if (response.statusCode == 200) {
      final List<dynamic> data = response.data;
      print(data.map((item) => Aluno.fromMap(item)).toList());
      return data.map((item) => Aluno.fromMap(item)).toList();
    } else {
      throw Exception('Erro ao obter alunos');
    }
  }
}

class Aluno {
  final String? id;
  final String? name;
  final String? email;
  final String? role;
  final String? lastName;
  final dynamic course;
  final List<dynamic>? enrollments;
  final dynamic address;
  final int? quantityEnrollments;
  final bool? enrollmentsEmpty;

  Aluno({
    this.id,
    this.name,
    this.email,
    this.role,
    this.lastName,
    this.course,
    this.enrollments,
    this.address,
    this.quantityEnrollments,
    this.enrollmentsEmpty,
  });

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
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
    };
  }

  factory Aluno.fromMap(Map<String, dynamic> map) {
    return Aluno(
      id: map['id'] as String?,
      name: map['name'] as String?,
      email: map['email'] as String?,
      role: map['role'] as String?,
      lastName: map['lastName'] as String?,
      course: map['course'],
      enrollments: map['enrollments'] != null
          ? List<dynamic>.from(map['enrollments'])
          : null,
      address: map['address'],
      quantityEnrollments: map['quantityEnrollments'] as int?,
      enrollmentsEmpty: map['enrollmentsEmpty'] as bool?,
    );
  }

  String toJson() => json.encode(toMap());

  factory Aluno.fromJson(String source) =>
      Aluno.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() {
    return 'Aluno(id: $id, name: $name, email: $email, role: $role, lastName: $lastName, course: $course, enrollments: $enrollments, address: $address, quantityEnrollments: $quantityEnrollments, enrollmentsEmpty: $enrollmentsEmpty)';
  }
}
