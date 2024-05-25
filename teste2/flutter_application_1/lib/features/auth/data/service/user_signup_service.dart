import 'package:dio/dio.dart';

class UserSignupService {
  final Dio _dio;

  UserSignupService(this._dio);

  Future<void> signupUser(
      {required String email,
      required String password,
      required String firstName,
      required String lastName}) async {
    try {
      await _dio.post('/signup', data: {
        'email': email,
        'password': password,
        'firstName': firstName,
        'lastName': lastName
      });
    } on DioError catch (e) {
      throw e;
    }
  }
}
