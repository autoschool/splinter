splinter
========

![Splinter!](http://img3.wikia.nocookie.net/__cb20130921120031/protagonist/ru/images/f/f9/22519539.jpg)

# Использование

```bash
echo Yarrr! && mvn jetty:run
```

# Смена базы данных

Управление подключением к базе данных задается в двух файлах: `src/main/resources/database.default.properties` и
`src/main/resources/database.local.properties`. Первый файл предназначен для хранения настроек для проекта в целом,
второй - для управления локальными подключениями на машине разработчика (т.е. трогать стоит только второй).
Поддерживаются следующие свойства:

| Свойство    | Описание                                                                                               |
|-------------|--------------------------------------------------------------------------------------------------------|
| db.driver   | Тип базы данных. Разрешенные значения: `h2 / mysql` (по умолчанию `р2`).                               |
| db.location | Расположение базы данных. Разрешенные значения: `network / file / memory` для H2, `network` для MYSQL. |
| db.name     | Имя базы данных (по умолчанию `splinter`).                                                             |
| db.path     | Путь к директории с базой данных (используется только при драйвере H2 и `db.location = file/network`). |
| db.host     | Имя хоста сервера базы данных (используется только при `db.location = network`).                       |
| db.port     | Порт сервера базы данных (используется только при `db.location = network`).                            |
| db.user     | Пользователь базы данных.                                                                              |
| db.password | Пароль пользователя базы данных.                                                                       |

Пример `database.local.properties`:

```
db.driver=mysql
db.location=network
db.user=root
db.password=insecureRootPassword
db.name=random_database
```