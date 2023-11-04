# TCC
Projeto prático idealizado para defesa de Tese de Conclusão de Curso em Graduação em Ciência da Computação na universidade Centro Universitário Carioca (Unicarioca)
## Introdução
O projeto tem como objetivo apresentar na prática a implementação de um Serviço Interno de Autenticação para controle de acessos das APIs dentro de arquiteturas de microsserviços.

A solução foi baseada em um sistema de redes sociais onde existem usuários, que podem criar relacionamentos uns com os outros e criar postagens. Desenvolvido em uma arquitetura de microsserviços, onde se benefícia do uso de serviços menores e desagregados em um escopo menor de cada "domínio" do negócio. No caso, foi criado um microsserviço de usuário e outro de postagens. Junto desses serviços que contém as regras de negócio, foram criados serviços para auxiliarem no funcionameto da arquitetura de microsserviços e considerados boas práticas: API Gateway e o Spring Eureka.

Por fim, o serviço principal do projeto é o Serviço Interno de Autenticação. É nele que será gerenciado e controlado todo acessos dos endpoints dos microsserviços.

**OBS:** Vale ressaltar que o intuito principal do projeto é validar o uso de um Serviço Interno de Autenticação, provar seus benefícios e servir de complemento para o TCC, não tendo foco no uso do sistema para um cenário real de redes sociais ou em um cenário real de microsserviço.

## Diferentes níveis de segurança
No projeto existe 3 branches principais:
### Main
**Cenário ideal:** Onde possui o Serviço Interno de Autenticação gerando e validando tokens entre comunicações cliente-serviço e serviço-serviço
### Unsafe-service2service-arch
**Cennário intermediário:** Onde possui o Serviço Interno de Autenticação gerando e validando tokens somente entre comunicações cliente-serviço
### Unsafe-arch
**Cenário crítico:** Onde não possui o Serviço Interno de Autenticação e existem diversas vulnerabilidades

## Como rodar?
Requisitos:
- Docker
- JDK 17

Os bancos utilizados no projeto são o PostgreSQL, MongoDB e Redis. Para facilitar eles estão sendo executados via containers com Docker. Basta entrar nas respectivas pastas de banco de dados e executar os docker-coompose.yml com:
```
docker-compose up -d
```
Todos os serviços foram desenvolvidos com Java junto do Spring, então basta rodar as aplicações Spring individualmente com sua IDE favorita ou via terminal

## Como funciona o sistema:
### Main
Para as comunicações internas entre os microsserviços se faz necessário o uso de um token de usuário (que deve ser passado no header com o nome "Authorization") e um token de serviço (que deve ser passado no header com o nome "service-authentication"). Em comunicações que sejam necessárias passar o usuário, ele será identificado diretamente pelo token JWT de usuário passado.
### Unsafe-service2service-arch
Para as comunicações internas entre os microsserviços se faz necessário o uso somente de um token de usuário (que deve ser passado no header com o nome "Authorization"). Em comunicações que sejam necessárias passar o usuário, ele será identificado diretamente pelo token JWT de usuário passado.
### Usafe-arch
Para as comunicações internas entre os microsserviços não é necessário o uso de tokens. Em comunicações que sejam necessárias passar o usuário, será passado diretamente no body da requisição.
