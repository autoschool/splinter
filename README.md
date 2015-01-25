splinter
========

![Splinter!](http://img3.wikia.nocookie.net/__cb20130921120031/protagonist/ru/images/f/f9/22519539.jpg)

# Использование

```bash
echo Yarrr! && mvn jetty:run
```

# Настройки

Настройки хранятся в property-файлах в папке `resources/configuration`. Настройки поделены на три окружения (dev, test,
prod), которое задается как property в `pom.xml` или ключом `-Denvironment=prod` при запуске; по умолчанию используется
`prod`. Splinter при этом достаточно хитер, чтобы для переопределения значений в `X.properties` достаточно было положить
рядом файл `X.local.properties` только с теми значениями, которые необходимо переопределить - например, в
`dev.local.properties` можно указать `location=FILE` для того, чтобы развернуть базу данных не в памяти, а во временном
файле.

# Смена базы данных

Управление подключением к базе данных задается в стандартном файле конфигурации -
`src/main/resources/configuration/${environment}.properties`. Для переопределения отдельных свойств рекомендуется
создавать файл `${environment}.local.properties` в той же папке - это позволит не трогать постоянные файлы приложения и
создать уютную локальную конфигурацию.

| Свойство    | Описание                                                                                                           |
|-------------|--------------------------------------------------------------------------------------------------------------------|
| database.driver   | Тип базы данных. Разрешенные значения: `H2 / MYSQL` (по умолчанию `H2`).                                     |
| database.location | Расположение базы данных. Разрешенные значения: `NETWORK / FILE / MEMORY` для H2, `NETWORK` для MYSQL.       |
| database.name     | Имя базы данных (по умолчанию `splinter`).                                                                   |
| database.path     | Путь к директории с базой данных (используется только при драйвере H2 и `database.location = FILE/NETWORK`). |
| database.host     | Имя хоста сервера базы данных (используется только при `database.location = NETWORK`).                       |
| database.port     | Порт сервера базы данных (используется только при `database.location = NETWORK`).                            |
| database.user     | Пользователь базы данных.                                                                                    |
| database.password | Пароль пользователя базы данных.                                                                             |

Пример:

```
database.driver=MYSQL
database.location=NETWORK
database.user=root
database.password=insecureRootPassword
database.name=random_database
```
