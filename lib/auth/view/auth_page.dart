import 'package:estagio_u/auth/view/auth_company.dart';
import 'package:flutter/material.dart';

class AuthPage extends StatefulWidget {
  const AuthPage({super.key});

  @override
  State<AuthPage> createState() => _AuthPageState();
}

class _AuthPageState extends State<AuthPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(8),
      decoration: BoxDecoration(color: Colors.green[800]),
      child: Column(
        children: [
          const Spacer(),
          Image.asset('assets/logo.png'),
          const Spacer(),
          Row(
            children: [
              const SizedBox(
                width: 8,
              ),
              Text(
                'Acesse como: ',
                style: Theme.of(context)
                    .textTheme
                    .bodyLarge!
                    .copyWith(fontSize: 20, color: Colors.white),
              ),
            ],
          ),
          Flexible(
            child: Container(
              padding: const EdgeInsets.all(8),
              margin: const EdgeInsets.all(8),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20), color: Colors.white),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Image.asset('assets/student.png', height: 50),
                  const SizedBox(width: 8),
                  Flexible(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Estudante',
                          style: Theme.of(context)
                              .textTheme
                              .bodyMedium!
                              .copyWith(
                                  color: Colors.green[800],
                                  fontWeight: FontWeight.bold,
                                  fontSize: 18),
                        ),
                        const SizedBox(
                          height: 4,
                        ),
                        Text(
                          'Visualize as vagas disponíveis para estágio.',
                          style: Theme.of(context)
                              .textTheme
                              .bodyMedium!
                              .copyWith(fontSize: 16),
                        )
                      ],
                    ),
                  )
                ],
              ),
            ),
          ),
          Flexible(
            child: GestureDetector(
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => AuthCompany()));
              },
              child: Container(
                padding: const EdgeInsets.all(8),
                margin: const EdgeInsets.all(8),
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(20),
                    color: Colors.white),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Image.asset('assets/empresario.png', height: 50),
                    const SizedBox(width: 8),
                    Flexible(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Empresa',
                            style: Theme.of(context)
                                .textTheme
                                .bodyMedium!
                                .copyWith(
                                    color: Colors.green[800],
                                    fontWeight: FontWeight.bold,
                                    fontSize: 18),
                          ),
                          const SizedBox(
                            height: 4,
                          ),
                          Text(
                            'Ofereça vagas para estágio na sua empresa.',
                            maxLines: 2,
                            style: Theme.of(context)
                                .textTheme
                                .bodyMedium!
                                .copyWith(fontSize: 16),
                          )
                        ],
                      ),
                    )
                  ],
                ),
              ),
            ),
          ),
          const Spacer()
        ],
      ),
    );
  }
}
