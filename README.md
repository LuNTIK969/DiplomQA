# ***Дипломный проект "QA"***

Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка. Сервис предлагает к приобретению туристические путевки. Приобретение продукта предполагается двумя способами:

1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает информацию о картах, а передает ее банковским сервисам:

- Payment Gate - сервис платежей, для первого варианта приобретения;
- Credit Gate - кредитный сервис, для приобретения в кредит по данным банковской карты.

Приложение сохраняет информацию исключительно о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## ***Documentation***
- [Задание](https://github.com/netology-code/qa-diploma)
- [План автоматизации тестирования](https://github.com/LuNTIK969/DiplomQA/blob/main/documentation/Plan.md)
- [Отчет по итогам тестирования](https://github.com/LuNTIK969/DiplomQA/blob/main/documentation/Report.md)
- [Общий отчет по итогам автоматизации](https://github.com/LuNTIK969/DiplomQA/blob/main/documentation/Summary.md)

***ПРЕДУСЛОВИЯ***

***1.*** На ПК должен быть установлен [Docker](https://www.docker.com/). В случае его отсутствия, установите согласно [инструкции](https://github.com/netology-code/aqa-homeworks/blob/master/docker/installation.md).

***2.*** На ПК должен быть установлен [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/). В случае его отсутствия, установите согласно [инструкции](https://github.com/netology-code/javaqa-homeworks/blob/master/intro/idea.md).

***3.*** Открыт в браузере [репозиторий](https://github.com/LuNTIK969/DiplomQA) GitHub.

***4.*** Используется Google Chrome версии 115 и старше.

## ***ИНСТРУКЦИЯ***

***1.*** Клонировать данный репозиторий согласно [инструкции](https://docs.github.com/ru/repositories/creating-and-managing-repositories/cloning-a-repository#cloning-a-repository)

***2.*** Открыть проект в программе IntelliJ IDEA.

***3.*** Запустить проект в терминале IntelliJ IDEA, команда:

```
docker-compose up
```

***2.*** Открыть новую вкладку в терминале и запустить SUT, команда:

- ***PostgreSQL***:

```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
```

- ***MySQL***:

```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
```

***3.*** Открыть новую вкладку в терминале, запустить автотесты, команда:

- ***PostgreSQL***:

```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```

- ***MySQL***:

```
./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```

***4.*** Сформировать отчет Allure по итогам проведенного тестирования, открыть в браузере, команды (выполняются поочередно):

```
./gradlew allureReport
```
```
./gradlew allureServe
```

***5.*** Остановить allureServe после формирования отчёта, комбинация клавиш **Ctrl + C**, после ввести **Y** и нажать Enter. Закрыть вкладку с терминалом.

***6.*** Завершить работу SUT. В терминале с запущенным SUT, ввести команду:

```
 Ctrl+C
```
Закрыть вкладку с терминалом.

***7.*** Остановить работу приложения использовав комбинацию клавиш **Ctrl + C**.

***8.*** Удалить все контейнеры и внутренние сети, связанные с этими сервисами, а так же указанные внутри тома, команда:
```
docker-compose down -v
``` 
