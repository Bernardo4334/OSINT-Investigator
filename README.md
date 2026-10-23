# OSINTigator43 — Android (v1.0)

Aplicação Android nativa em Java, com interface escura e sem dependências externas de bibliotecas.

## Funcionalidades
- Gera atalhos para pesquisa pública de usernames em Instagram, TikTok, GitHub, Reddit, X e Pinterest.
- Consulta registos DNS A através do Google Public DNS (requer Internet).
- Valida localmente o formato de um endereço de email, sem tentar descobrir contas associadas.
- Guarda os últimos resultados localmente e permite partilhar/exportar texto.
- Não exige conta e não integra anúncios.

## Compilar o APK
Requer Android Studio ou Gradle 8.9 + Android SDK Platform 35. Abra a pasta do projeto no Android Studio e escolha **Build > Build APK(s)**. O APK de debug deverá ficar em `app/build/outputs/apk/debug/app-debug.apk`.

Também existe um workflow GitHub Actions em `.github/workflows/build-apk.yml` que compila o APK e publica-o como artefacto quando o projeto é enviado para um repositório GitHub.

## Limitações importantes
- A pesquisa de username gera links de perfis públicos; não faz verificação automática da existência de contas.
- A análise de email apenas valida o formato; não confirma a existência da caixa de correio nem procura contas associadas.
- DNS A não é uma avaliação de reputação nem um scanner de segurança.
- O APK de debug não é uma versão assinada para distribuição pública.
