[hugo]: https://github.com/HugoCarvalho99
[hugo-pic]: https://github.com/HugoCarvalho99.png?size=120
[nelson]: https://github.com/nelsonmestevao
[nelson-pic]: https://github.com/nelsonmestevao.png?size=120
[pedro]: https://github.com/pedroribeiro22
[pedro-pic]: https://github.com/pedroribeiro22.png?size=120
[rui]: https://github.com/ruimendes29
[rui-pic]: https://github.com/ruimendes29.png?size=120
[ricardo]: https://github.com/ricardoslv
[ricardo-pic]: https://github.com/ricardoslv.png?size=120

<div align="left">
    <img src="src/main/resources/images/logo.png" alt="Share Your Media" width="500px">
</div>

> Share Your Media

## :rocket: Getting Started

This project uses a MySQL database for data persistence. You should have MySQL
up and running. For setting up a successful connection, the environment
variables must be set. To obtain the songs you have got to have a running server
instance.

```bash
cp .env.sample .env
```

After this, you must fill in the fields correctly and export them in your
environment. Checkout [direnv](https://direnv.net/) for your shell and
[EnvFile](https://github.com/Ashald/EnvFile) for IntelliJ.

### :hammer: Development

Compile and run the client in a clean build.

```
mvn clean compile exec:java
```

Start a server instance.

```
mvn exec:java@server
```

Running tests.

```
mvn test
```

Format the code accordingly to common guide lines.

```
mvn formatter:format
```

Lint your code with _checkstyle_.

```
mvn checkstyle:check
```

### :package: Deployment

Bundling the app into jar file.

```
mvn package
```

### :hammer_and_wrench: Tools

The recommended Integrated Development Environment (IDE) is IntelliJ IDEA. For
the design it's recommended to install JavaFX Scene Builder.

## :busts_in_silhouette: Team

[![Hugo][hugo-pic]][hugo] | [![Nelson][nelson-pic]][nelson] | [![Pedro][pedro-pic]][pedro] | [![Rui][rui-pic]][rui] | [![Ricardo][ricardo-pic]][ricardo] 
:---: | :---: | :---: | :---: | :---:
[Hugo Carvalho][hugo] | [Nelson Estevão][nelson] | [Pedro Ribeiro][pedro] | [Rui Mendes][rui] | [Ricardo Silva][ricardo]
