<<<<<<< HEAD
# SP_SpringBoot_AWS_Desafio_03_pedido-estoque-client
Repositorio para Desafio03
=======
Desafio 03: Microserviços com Spring Boot e AWS
📋 Descrição do Projeto
O Desafio 03 consiste no desenvolvimento de um sistema de microserviços em Java utilizando Spring Boot, simulando um sistema de gerenciamento de pedidos, estoque e clientes. Esses microserviços se comunicam por chamadas REST e devem ser implantados em instâncias AWS EC2.

🎯 Objetivo
Criar três microserviços independentes, cada um com uma responsabilidade específica:

Pedido: Gerenciamento de pedidos (criação, atualização e consulta).
Estoque: Controle do estoque de produtos.
Cliente: Cadastro e gerenciamento de clientes e histórico de pedidos.
Esses serviços devem se comunicar utilizando REST APIs, garantindo integração eficiente e correta.

🛠️ Requisitos Técnicos
1. Estrutura do Projeto
Cada microserviço deve ser desenvolvido como um projeto Spring Boot independente.
Utilizar Spring Data JPA para persistência de dados com PostgreSQL.
Implementar logs e documentação dos endpoints usando Log4j2 e Swagger.
2. Comunicação Entre Microserviços
Configurar comunicação REST entre os serviços.
Utilizar OpenFeign para facilitar a comunicação (ou RestTemplate como alternativa).
3. Implantação
Implantar os serviços em instâncias EC2 da AWS.
Configurar Elastic Load Balancer (ELB) para distribuir o tráfego.
Garantir segurança de rede com VPC e Security Groups.

🚀 Configuração e Implantação
1. Ambiente de Desenvolvimento
Pré-requisitos:

Java JDK 17+
Docker e Docker Compose
PostgreSQL
Maven

Implantação na AWS
Criar instâncias EC2 para cada microserviço.
Configurar as instâncias com:
Ubuntu Server ou Amazon Linux 2
Docker
Java
Implantar os serviços em contêineres Docker e configurar o balanceador de carga ELB.

📊 Arquitetura
Microserviços
Pedido:

Criação de pedidos com validação de estoque.
Atualização automática do estoque em caso de sucesso.

Estoque:

Controle de produtos e quantidades disponíveis.
Exposição de endpoints para consulta e atualização.

Cliente:

Gerenciamento de clientes.
Consulta de histórico de pedidos.
Comunicação
REST APIs: Comunicação entre serviços.
Balanceador de carga: Distribuição de requisições.

✨ Contribuição
Branches:
Criar uma branch por funcionalidade.
Commits:
Utilize mensagens de commits claras e padronizadas.
Pull Requests:
Abrir PRs para integrar alterações, garantindo revisão de código.
>>>>>>> cfb7491 (readme feito)
