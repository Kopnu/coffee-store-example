## Что это?
Это пример back-end части web приложения - магазина по продаже кофе. Написан с целью самообучения FE разработчиков. 

## Запуск
### Запуск из исходников в докере
1. Клонировать данный репозиторий <br>
   ``git clone git@github.com:Kopnu/coffee-store-example.git``
2. Запустить командную строку из папки проекта
3. ``./gradlew clean build``
4. Запустить docker-compose <br>
   ``docker compose up -d --build``
5. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/)

### Запуск из Jar
1. Установить [Java 21 JRE](https://adoptium.net/temurin/releases/?package=jre), если не установлена. (В целом подойдёт любая java 21+)
2. Скачать последний релиз [отсюда](https://github.com/Kopnu/coffee-store-example/releases/tag/v0.0.1)
3. Выполнить команду в командной строке из папки, куда скачали релиз:
   ``java --add-opens java.base/java.lang=ALL-UNNAMED -jar shop-example-impl-0.0.1-SNAPSHOT.jar``
4. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/)

### Запуск из исходников
1. Установить [Java 21 JRE](https://adoptium.net/temurin/releases/?package=jre), если не установлена. (В целом подойдёт любая java 21+)
2. Клонировать данный репозиторий <br>
   ``git clone git@github.com:Kopnu/coffee-store-example.git``
3. Запустить командную строку из папки проекта
4. ``./gradlew clean build``
5. После завершения. Запустить приложение: 
   ``java --add-opens java.base/java.lang=ALL-UNNAMED -jar ./shop-example-impl/build/lib/shop-example-impl-0.0.1-SNAPSHOT.jar``
6. Проверить работоспособность зайдя в [API документацию](http://localhost:8080/swagger-ui/index.html#/) 

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
A: Убедитесь в корректности установки java, введя ``java --version``, должен быть ответ и версия 21+

Q: Версия корректная, но ошибка воспроизводится<br>
A: Попробуйте: ``set JAVA_HOME=path/to/java-21`` подставив полный путь до Java 21 на вашей машине

Q: Web server failed to start. Port 8080 was already in use.
A: Порт чем-то занят. Возможно вы запускаете второй инстанс приложения. Закройте предыдущий и повторите попытку.
