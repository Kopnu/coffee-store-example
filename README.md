## Что это?
Это пример back-end части web приложения - магазина по продаже кофе. Написан с целью самообучения FE разработчиков. 

## Запуск
### Запуск из исходников в докере
0. Клонировать данный репозиторий <br>
   ``git clone git@github.com:Kopnu/coffee-store-example.git``
1. Запустить docker-compose <br>
   ``docker compose up -d --build``
2. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/)

### Запуск из Jar
0. Установить [Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows), если не установлена.
1. Скачать последний релиз [отсюда](https://github.com/Kopnu/coffee-store-example/releases/tag/v0.0.1)
2. Выполнить команду в командной строке из папки, куда скачали релиз:
   ``java --add-opens java.base/java.lang=ALL-UNNAMED -jar shop-example-impl-0.0.1-SNAPSHOT-spring-boot.jar``
5. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/)

### Запуск из исходников
0. Установить [Java 17](https://www.oracle.com/java/technologies/downloads/#jdk17-windows), если не установлена.
1. Клонировать данный репозиторий <br>
   ``git clone git@github.com:Kopnu/coffee-store-example.git``
2. Запустить командную строку из папки проекта
3. ``./mvnw clean package``
4. После завершения. Запустить приложение: 
   ``java --add-opens java.base/java.lang=ALL-UNNAMED -jar ./shop-example-impl/target/shop-example-impl-0.0.1-SNAPSHOT-spring-boot.jar``
5. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/) 

## Авторизация 
По умолчанию запросы идут от роли GUEST (endpoints без секьюрити)
Для авторизации необходимо в сваггере нажать на зелёную кнопку __Authorize__ и ввести одну из следующих пар Login/Pass:

| Login | Pass  | Role  |
|-------|-------|-------|
| admin | admin | ADMIN |
| user1 | 123   | USER  |
| user2 | 321   | USER  |

## FAQ

Q: Ошибка _invalid target release 17_<br>
A: Убедитесь в корректности установки java, введя ``java --version``, должен быть ответ и версия 17+

Q: Версия корректная, но ошибка воспроизводится<br>
A: Попробуйте: ``set JAVA_HOME=path/to/java-17`` подставив полный путь до java 17 на вашей машине

Q: Web server failed to start. Port 8080 was already in use.
A: Порт чем-то занят. Возможно вы запускаете второй инстанс приложения. Закройте предыдущий и повторите попытку.
