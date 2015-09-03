Project Test
========

## Arquitetura

Back end: Framework Srping Web: Web MVC (Model, View, Controller), Banco de dados relacional, API RestFul
Front end: Framework AngularJS: MVVM (Model, View, ViewModel).


## Requisitos Back-End
- Maven | [http://maven.apache.org/](http://maven.apache.org/)

- JDK8 Oracle/Sun | [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

- Lombok | [http://projectlombok.org/](http://projectlombok.org/)

Lombok instalação:
Rode:
```
java -jar ~/.m2/repository/org/projectlombok/lombok/1.16.2/lombok-1.16.2.jar
```
Após rodar, será necessário especificar a pasta que o Eclipse esteja instalado, selecione a pasta 'eclipse' para a instalação na pasta do Eclipse e click em Install.

## Back-End
Dentro da pasta server do projeto rode:

```
mvn eclipse:eclipse
```
- após rodar e o resultado for Build success, importe o projeto no seu Eclipse. (o Lombok precisará estar instalado para que não ocorra erros)

Crie uma base de dados chamada 'project-test' o usuário deve ser: "root" e sem senha, ou senha em branco, caso queira mudar, as configurações estão no arquivo: application.properties dentro da pasta 'resource', nos parâmetros: 

```
spring.datasource.username=root
spring.datasource.password=
```

- Após isso para rodar o back end do projeto entre na classe Application.java, clique com o botão do direito do mouse em cima do método main, Run As > Java Application:
- Método main:
```
public static void main(String[] args) throws Exception {
	new SpringApplicationBuilder(Application.class).run(args);
}
```
- Após o projeto rodar, ele criará as tabelas necessárias no banco de dados, e então precisaremos popular as tabelas "state", "city" e "zipcode", os arquivos .SQL para importar estão nos links: 
- Tabela "city" e "state" | [http://www.dropbox.com/s/9izy2eb1yiif7tx/cityAndState.sql?dl=0](http://www.dropbox.com/s/9izy2eb1yiif7tx/cityAndState.sql?dl=0)
- Tabela "zipcode" | [http://www.dropbox.com/s/01ka6h1zmfsyl79/zipcode.sql?dl=0](http://www.dropbox.com/s/01ka6h1zmfsyl79/zipcode.sql?dl=0)

Para a fácil importação utilizar o app Sequel Pro (MAC only) - | [http://www.sequelpro.com/download](http://www.sequelpro.com/download)

## Requisitos Front-End

- Instalar HomeBrew

```
  ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```


- Instalar Git
```
sudo apt-get install git

```

- Instalar NodeJS
```
cd ~/

git clone https://github.com/visionmedia/n.git

cd n

make install

FOR UBUNTU
sudo apt-get install curl

FOR MAC OS
brew install curl

sudo n stable

```

- Install Global Modules
```
sudo npm install -g gulp

sudo npm install -g bower

```

- Instalar Node Modules
```
npm install

```

- Instalar Bower Components
```

bower install

```

- Rodar Front end
- Dentro da pasta client rode:
```
gulp serve

