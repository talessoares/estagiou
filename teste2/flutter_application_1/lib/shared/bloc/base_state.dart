import 'package:equatable/equatable.dart';

abstract class BaseState extends Equatable {}

class InitialState extends BaseState {
  @override
  List<Object?> get props => [];
}

class LoadingState extends BaseState {
  @override
  List<Object?> get props => [];
}

class SuccessState<T> extends BaseState {
  final T data;

  SuccessState(this.data);

  @override
  List<Object?> get props => [data];
}

class ErrorState extends BaseState {
  final String message;

  ErrorState(this.message) {
    print("ERRO: $message");
  }
  @override
  List<Object?> get props => [message];
}
