# ***Отчет***

Проведено автоматизированное тестирование комплексного сервиса по приобретению туристических путевок. В ходе тестирования, проверялась заявленная поддержка двух БД:

***MySQL***  

***PostgreSQL***.

## ***Тест-кейсы***

- Всего - ***58*** test cases
- Успешных - ***34*** test cases --> ***58,62%***;
- Неуспешных - ***24*** test cases --> ***41,38%***.

### ***Отчет Allure:***

![Allure Report - 1](https://github.com/LuNTIK969/DiplomQA/assets/119416858/b6931251-9bf4-4a76-824b-22108119f4cb)

### ***Отчет Gradle:***

![Gradle ](https://github.com/LuNTIK969/DiplomQA/assets/119416858/e1fbc072-9c31-4f70-9791-dbc0cf327086)

### ***Общие рекомендации:***

***1.*** Перечень выявленных дефектов - [Issues](https://github.com/LuNTIK969/DiplomQA/issues).

***2.*** Требуется внести корректировку в орфографию слова "Марракэш", так как правильное написание - "Марракеш".

***3.*** Заменить предупреждение "Неверный формат" на более понятное и информативное сообщение, которое подчеркнет важность заполнения данного поля, пример: "Извините, но введенные вами данные некорректны. Пожалуйста, укажите правильные значения в поле.".

***4.*** Ограничить возможность введения данных в поле "Владелец" латинскими буквами. Добавить автоформатирование до требуемого формата текста (upper, lower) 

### ***Вывод:***

Учитывая общее количество ошибок, включая критические, текущая версия приложения не готова к релизу и требует доработки.
