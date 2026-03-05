Literalura 📚 - Challenge Alura Backend

O Literalura é uma aplicação de console desenvolvida em Java com Spring Boot, que permite a interação com a API Gutendex para criar um catálogo de livros personalizado. O projeto foca em persistência de dados, consumo de APIs REST e organização lógica de informações.


🚀 Funcionalidades

O sistema oferece um menu interativo com as seguintes operações:

Busca de Livros por Título: Consulta a API externa, converte o JSON e salva o livro e seu autor no banco de dados local.

Listagem de Livros: Exibe todos os títulos já catalogados no banco de dados PostgreSQL.

Listagem de Autores: Mostra os autores registrados, detalhando seus anos de nascimento e falecimento.

Filtro de Autores Vivos: Permite consultar quais autores estavam vivos em um determinado ano específico.

Filtro por Idioma: Lista as obras registradas de acordo com a sigla do idioma (ex: pt, en, es).



🛠️ Tecnologias Utilizadas

Tecnologia	Finalidade

Java 17	Linguagem principal do projeto

Spring Boot 3	Framework para agilizar o desenvolvimento e configuração

Spring Data JPA	Abstração para facilitar a comunicação com o banco de dados

PostgreSQL	Banco de dados relacional para persistência dos dados

Jackson	Biblioteca para desserialização de JSON da API Gutendex



📋 Pré-requisitos e Configuração

Para rodar o projeto localmente, siga os passos:

Ter o PostgreSQL instalado e rodando.

Criar um banco de dados chamado literalura.


⚙️Configurar as credenciais no arquivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
