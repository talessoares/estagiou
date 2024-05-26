import 'package:flutter_application_1/features/auth/data/service/user_signup_service.dart';
import 'package:flutter_application_1/features/auth/view/bloc/sign_up_bloc/sign_up_events.dart';
import 'package:flutter_application_1/shared/bloc/base_state.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SignUpBloc extends Bloc<SignUpEvent, BaseState> {
  final UserSignupService _service;

  SignUpBloc(this._service) : super(LoadingState()) {
    on<OnSignUpEvent>((event, emit) async {
      emit(LoadingState());
      try {
        final result = await _service.signupUser(alunoDTO: event.alunoDTO);
        emit(SuccessState(result));
      } catch (e) {
        emit(ErrorState(e.toString()));
      }
    });
    on<OnGetAlunosEvent>(
      (event, emit) async {
        emit(LoadingState());
        await _service.getAllAlunos();
        emit(SuccessState(null));
      },
    );
  }
}
