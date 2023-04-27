# Tasklist

Здесть появится распределение задач

### Никита:

- [ ] Первый коммит
- [ ] Создание таблиц на всех страницах (В первом коммите разобьёшь эту задау на части)
   - [ ] Страница ...

### Егор:

- [ ] Первый коммит
- [ ] Настройка основной Stage
- [ ] Создание контроллеров для навигационых кнопок и основных сцен (по сценам обращаться к Пете) 
- [ ] Создание поиска под все страница (разбей на подзадачи)
   - [ ] Поиск на странице...

### Заебали хуи пинать. TODO

- [ ] Main page
- [X] Service page
  - [X] Table
    - [X] Edit buttons
  - [X] Filter
  - [ ] Style
  - [X] Add new service
- [ ] Services page
  - [ ] Table
    - [ ] Edit buttons
  - [ ] Add new services
- [ ] Employees page
  - [ ] Table
    - [ ] Buttons
  - [ ] Filters
  - [ ] Add new
- [ ] Client Page
  - [ ] Table
    - [ ] Buttons
  - [ ] Filters
  - [ ] Add new

 
# Документация
JavaFX progect for beauty saloon

* [Загрузить проект](https://github.com/mesor21/OgreBeauty#%D0%B7%D0%B0%D0%B3%D1%80%D1%83%D0%B7%D0%B8%D1%82%D1%8C-%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82)
* [IDE](https://github.com/mesor21/OgreBeauty#ide)
* [Теория](https://github.com/mesor21/OgreBeauty#%D1%82%D0%B5%D0%BE%D1%80%D0%B8%D1%8F)
* [С чем взаимодействовать](https://github.com/mesor21/OgreBeauty#%D1%81-%D1%87%D0%B5%D0%BC-%D0%B2%D0%B7%D0%B0%D0%B8%D0%BC%D0%BE%D0%B4%D0%B5%D0%B9%D1%81%D1%82%D0%B2%D0%BE%D0%B2%D0%B0%D1%82%D1%8C)


## IDE

Что б вам было удобно работать с проектом, вам требуется скачать IDE [IntelliJ IDEA](https://www.jetbrains.com/idea/download)

После открытия приложения у вас появится это окно

![intel](https://learn.microsoft.com/ru-ru/azure/hdinsight/spark/media/apache-spark-create-standalone-application/spark-1.png)

Для открытия проекта требуется выполнить следующие действия:
1. В этом окне нажимаем кнопку "Get frin VCS"
2. Вставляем следующую ссылку в поле URL:
```
https://github.com/mesor21/OgreBeauty.git 
```
3. Устанавливаем под ней место куда сохранить файл
4. Далее подтверждаем все что предложит вам IDE
5. После открытия проекта посмотрите справа внизу не идёт какая-либо загрузка. Если нет
   * Выбираем в колонке справа "pom.xml"
   * Нажимаем на файл ПКМ
   * Выбираем пункт "Maven"
   * Нажимаем "Reload project"
   * Ждём пока всё подгрузится

Что б работать с git внутри IDE вам потребуется либо авторизоваться, либо сгенерировать токен:

С авторизацией по факту уже разберётесь

Генерация токена:
* На 7 этапе поставьте максимум
* На 8 этапе можете выбирать все галочки
* На 9 этапе сохраните его к себе отдельным файлом что б не генерировать потом ещё раз

[Создание токена](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)

Коммиты будут на ветке main

## Теория

Прежде чем написать мне используем все ресурсы в этом и следующем пунке, а потом пишем мне.
* [Теория по JavaFX habr (прочитать всё кроме 7)](https://habr.com/ru/post/474498)
* [Дизайн JavaFX](https://habr.com/ru/company/first/blog/673608/)
* [Google](https://google.com)
* [ChatGPT на русском](https://t.me/RussiaChatGPTBot)


Так как на ваших плечах будет лежать непростая задача:
Сделать фронт к приложению. Для решения этой задачи в вашем распоряжении будут service, controller и .fxml (ссылки на папки и файлы в следующем пункте).
В Main.java у вас есть метод start. Выставляйте параметры которые нужно.


<h4>Специально для Егора</h2>>
Пишем названия переменных понятным АНГЛИЙСКИМ ЯЗЫКОМ. Если не знаешь как назвать, вот тебе

[Гениальное решение проблемы Егора](https://translate.google.com/)

## С чем взаимодействовать


1. [src/main/java/com/example/ogrebeauty](https://github.com/mesor21/OgreBeauty/tree/main/src/main/resources/com/example/ogrebeauty)
2. [src/main/resources/com/example/ogrebeauty](https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty)
   * [controller](https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty/controller)
   * [service](https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty/service)
   * [Main.java](https://github.com/mesor21/OgreBeauty/blob/main/src/main/java/com/example/ogrebeauty/Main.java)

В основном взаимодействие с сервисами будут идти через объекты.


Советую ознакомится со всеми методами service так как взаимодействие со всеми данными у вас будет производится через service(обязательно посмотрите ServiceService).
Желаю удачи!

## Подключение БД

Для теста интерфейсам вам потребуется использовать базу данных. В нашем случае используется Postgresql.
На этом этапе разрешаю звать меня для помощи

**Для Егора:**

[Ссылка для скачивания](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

Качаешь последнюю версию

[Установка](https://winitpro.ru/index.php/2019/10/25/ustanovka-nastrojka-postgresql-v-windows/)
(Выполнить первые 2 пункта)
Поставь пароль:
```
postgresql
```
Заходишь в консоль windows и используешь следующие команды
```
CD C:\Program Files\PostgreSQL\
```
```
dir
```
На этом этапе у тебя высветится папка с номером версии
```
CD ./*версия/bin
```
```
createdb -U postgres ogrebeauty
```

Следующей командой проверяешь создалась ли база с названием "ogrebeauty"
```
Psql -U postgres –l
```

**Для Никиты:**

[Установка](https://dataschool.com/learn-sql/how-to-start-a-postgresql-server-on-mac-os-x/)
(Остановится на команде: "psql postgres")

Используй следующую команду что б установит пароль к пользователю
```
ALTER USER postgres PASSWORD 'postgresql';
```
Создаёшь новую бд с названием "ogrebeauty"
```
CREATE DATABASE ogrebeauty;
```
Проверка создания
```
\l
```

**Общая часть**

Если все предыдущие шаги выполнены корректно, то переходим в проект и создаём таблицы. Для этого я специально сделал отдельную функцию.
* Открываем файл Main.java
* Запускаем проект
* Если в консоли появилось сообщение "Соединение с базой данных успешно установлено." переходим к следующему пункту. Если нет, то где-то вы ошиблись во время установки.
* (Все параметры подключения можно отредактировать в файле [repository/PostgreSQLJDBC.java](https://github.com/mesor21/OgreBeauty/blob/main/src/main/java/com/example/ogrebeauty/repository/PostgreSQLJDBC.java))
* **В "Main.java" закомментирована строчка отвечающая за создание таблиц в базе. После того как её раскомментируешь и запустишь проект 1 раз удали её обязательно после чего проект будет полностью работать с базой данных.**
