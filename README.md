# Tasklist

Здесть появится распределение задач

Никита:

- [ ] Первый коммит

Егор:

- [ ] Первый коммит

Links:


# Документация
JavaFX progect for beauty saloon



## Загрузить проект**

Что б загрузить этот проект требуется:

**Для Егора:**
Скачать GitHub Desktop
```
https://www.jetbrains.com/idea/download
```
Как скачать проект (Мне похер что тут на английском всё. По картинкам разберёшься)
```
https://docs.github.com/en/desktop/contributing-and-collaborating-using-github-desktop/adding-and-cloning-repositories/cloning-a-repository-from-github-to-github-desktop
```
**Для Никиты:**
```
https://git-scm.com/download/mac
```
Открываешь в консоли папку куда хочешь сохранить проет и вставляешь туда эту команду:
```
git clone https://github.com/mesor21/OgreBeauty.git 
```

## IDE

Что б вам было удобно работать с проектом, вам требуется скачать IDE IntelliJ IDEA
```
https://www.jetbrains.com/idea/download
```

После открытия приложения у вас появится это окно

![intel](https://learn.microsoft.com/ru-ru/azure/hdinsight/spark/media/apache-spark-create-standalone-application/spark-1.png)

Для открытия проекта требуется выполнить следующие действия:
1. В этом окне нажимаем кнопку "Open"
2. После открываем папку которая у вас скачалась с git
3. Заходим в неё
4. Выбираем файл с названием "pom.xml"
5. Далее подтверждаем все что предложит вам IDE

Что б работать с git внутри IDE вам потребуется сгенерировать токен:

* На 7 этапе поставьте максимум
* На 8 этапе можете выбирать все галочки
* На 9 этапе сохраните его к себе отдельным файлом что б не генерировать потом ещё раз

Создание токена
```
https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token
```
Не знаю. Наверное все коммиты будут на ветке main


## Теория

Прежде чем написать мне используем все ресурсы в этом и следующем пунке, а потом пишем мне.
* Теория по JavaFX habr (прочитать всё кроме 7)
```
https://habr.com/ru/post/474498
```
* Google
```
https://google.com
```
* ChatGPT на русском
```
https://t.me/RussiaChatGPTBot
```


Так как на ваших плечах будет лежать непростая задача:
Сделать фронт к приложению. Для решения этой задачи в вашем распоряжении будут service, controller и .fxml (ссылки на папки и файлы в следующем пункте).
В Main.java у вас есть метод start. Выставляйте параметры которые нужно.


<h4>Специально для Егора</h2>>
Пишем названия переменных понятным АНГЛИЙСКИМ ЯЗЫКОМ. Если не знаешь как назвать, вот тебе

```
https://translate.google.com/
```
## С чем взаимодействовать


1. src/main/java/com/example/ogrebeauty
2. src/main/resources/com/example/ogrebeauty
   * controller
   * service
   * Main.java

Советую ознакомится со всеми методами service так как взаимодействие со всеми данными у вас будет производится через service(обязательно посмотрите ServiceService). 
Желаю удачи!