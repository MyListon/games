# Projeto Loja de Games - Backend com Spring Boot
 
<br />
 
<div align="center">
<img src="https://i.imgur.com/w8tTOuT.png" title="source: imgur.com" /> 
</div>
 
<br />
 
<div align="center">
<img src="https://img.shields.io/github/languages/top/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/github/repo-size/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/github/languages/count/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/github/last-commit/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/github/issues/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/github/issues-pr/MyListon/games?style=flat-square" />
<img src="https://img.shields.io/badge/status-construção-yellow" alt="Status: Em Construção">
 
</div>
 
<br />
 
## 1. Descrição
 
<br />
 
O projeto **Loja de Games** consiste no desenvolvimento do **Backend** para um e-commerce de jogos eletrônicos. Esta aplicação simula um sistema de gerenciamento de estoque e catálogo, onde os **Produtos** são cadastrados e obrigatoriamente classificados por **Categorias**.
 
Este projeto foi desenvolvido como **Atividade Prática** proposta pela Generation Brasil, focado na aplicação dos conceitos de API REST, persistência de dados e boas práticas com Spring Boot.
 
<br />
 
## 2. Sobre esta API
 
<br />
 
A API da Loja de Games foi desenvolvida utilizando **Java** e o **framework Spring Boot**, seguindo a arquitetura em camadas (Model, Repository, Controller) e os princípios REST. O foco da API é o gerenciamento completo do CRUD (6 métodos) para os recursos `Categoria` e `Produto`.
 
<br />
 
### 2.1. Principais funcionalidades da API:
 
<br />
 
1. **CRUD Completo (6 métodos)** para o recurso **Produto** (Jogos) e **Categoria** (Gêneros).
2. Implementação do **Relacionamento One-to-Many** entre Categoria e Produto.
3. Utilização de **Spring Data JPA** para conexão com o banco de dados **MySQL**.
4. Testes e validação de todos os 12 endpoints via Insomnia.

<br />
 
## 3. Diagrama de Classes
 
<br />
 
O diagrama abaixo representa a estrutura e o relacionamento entre as principais entidades do projeto.

<br />
 
```mermaid
classDiagram
class Produto {
  - id : Long
  - nome : String
  - preco : BigDecimal
  - quantidade : Integer
  - categoria : Categoria
}
class Categoria {
  - id : Long
  - nome : String
  - descricao : String
  - produtos : List<Produto>
}
Categoria "1" -- "0..*" Produto : classifica
 
```
 
<br />
 
## 4. Diagrama Entidade-Relacionamento (DER)
 
O DER (Diagrama Entidade-Relacionamento) representa como os dados estão estruturados e interligados no banco de dados relacional (MySQL).
 

```mermaid
erDiagram
    tb_categorias ||--o{ tb_produtos : classifica
    tb_categorias {
        bigint id PK
        varchar nome
        varchar descricao
    }
    tb_produtos {
        bigint id PK
        varchar nome
        decimal preco
        int quantidade
        bigint categoria_id FK
    }
```

 
## 5. Tecnologias utilizadas
 
<br />
 
| Item                          | Descrição       |
| ----------------------------- | --------------- |
| **Servidor**                  | Tomcat          |
| **Linguagem de programação**  | Java            |
| **Framework**                 | Spring Boot     |
| **ORM**                       | JPA + Hibernate |
| **Banco de dados Relacional** | MySQL           |
| **Validação**                 | Jakarta         |
| **Build Tool**                | Maven           |
| **Autenticação**              | JWT             |
| **Testes automatizados**      | JUnit           |
| **Documentação**              | SpringDoc       |
 
<br />
 
## 6. Requisitos
 
<br />
 
Para executar os códigos localmente, você precisará:
 
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Banco de dados [MySQL](https://dev.mysql.com/downloads/)
- [STS](https://spring.io/tools)
- [Insomnia](https://insomnia.rest/download) ou [Postman](https://www.postman.com/)
 
<br />
 
## 7. Como Executar o projeto no STS
 
<br />
 
### 7.1. Importando o Projeto
 
1. Clone o repositório do Projeto [Loja de Games](https://github.com/MyListon/games.git) dentro da pasta do *Workspace* do STS
 
```bash
git clone https://github.com/MyListon/games.git
```
 
2. **Abra o STS** e selecione a pasta do *Workspace* onde você clonou o repositório do projeto
3. No menu superior do STS, clique na opção: **File 🡲 Import...**
4. Na janela **Import**, selecione a opção: **General 🡲 Existing Projects into Workspace** e clique no botão **Next**
5. Na janela **Import Projects**, no item **Select root directory**, clique no botão **Browse...** e selecione a pasta do Workspace onde você clonou o repositório do projeto
6. O STS reconhecerá o projeto automaticamente
7. Marque o Projeto Loja de Games no item **Projects** e clique no botão **Finish** para concluir a importação
 
<br />
 
### 7.2. Executando o projeto
 
1. Na Guia **Boot Dashboard**, localize o  **Projeto Loja de Games**
2. Selecione o **Projeto Loja de Games**
3. Clique no botão **Start or Restart** <img src="https://i.imgur.com/wdoZqWP.png" title="source: imgur.com" width="4%"/> para iniciar a aplicação
4. Caso seja perguntado se você deseja autorizar o acesso ao projeto via rede, clique no botão **Permitir Acesso**
5. Acompanhe a inicialização do projeto no console do STS
6. Verifique se o banco de dados `db_games` foi criado corretamente e se as tabelas foram geradas automaticamente.
7. Utilize o [Insomnia](https://insomnia.rest/) ou o [Postman](https://www.postman.com/) para testar os endpoints.
 
<br />
 
> [!TIP]
>
> Ao acessar a URL `http://localhost:8081` em seu navegador, a interface do Swagger será carregada automaticamente, permitindo a visualização e a interação com os endpoints da API, bem como a consulta dos modelos de dados utilizados.
 
<br />
 
## 8. Como Executar os Testes no STS
 
### 8.1. **Localizando as Classes de Teste**
 
- Na **Package Explorer**, navegue até a Source Folder `src/test/java`
- Localize as classes que contém os testes (classes cujo nome terminam com a palavra **Test**)
 
<br />
 
### 8.2. **Executando os Testes**
 
Você pode executar os testes de duas formas:
 
#### 👉 Opção 1: Executar uma classe de teste específica
 
- Clique com o botão direito sobre a classe de teste
- Selecione a opção `Run As > JUnit Test`
 
#### 👉 Opção 2: Executar todos os testes do projeto
 
- Clique com o botão direito sobre a pasta do projeto
- Selecione: `Run As > JUnit test`
 
<br />
 
### 8.3. **Verificando os Resultados**
 
- Ao executar os testes, na **Package Explorer**, será exibida a guia **JUnit**  mostrando os resultados dos testes
- Os testes que falharem serão destacados em vermelho, e os bem-sucedidos em verde
- Clique nos testes para visualizar os detalhes ou mensagens de erro no item **Failure Trace**
 
<br />
 
## 9. Contribuição
 
<br />
 
Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:
 
- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java!
 
<br />
 
##  10. Contato
 
<br />
 
Desenvolvido por [**Myriam**](https://github.com/MyListon)
Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue!