# Tasklist

Здесть появится распределение задач

Никита:

- [ ] Первый коммит

Егор:

- [ ] Первый коммит

Links:
* <Link href=""></Link>
* <Link href=""></Link>

# Документация
JavaFX progect for beauty saloon



**<h2>Загрузить проект</h2>**

Что б загрузить этот проект требуется:

Для Егора:
<Button primary link href="https://www.jetbrains.com/idea/download">
Скачать GitHub Desktop
</Button>
<Button primary link href="https://docs.github.com/en/desktop/contributing-and-collaborating-using-github-desktop/adding-and-cloning-repositories/cloning-a-repository-from-github-to-github-desktop">
Как скачать проект (Мне похер что тут на английском всё. По картинкам разберёшься)
</Button>

Для Никиты:
<Button primary link href="https://git-scm.com/download/mac">
Скачать GitHub
</Button>

Открываешь в консоли папку куда хочешь сохранить проет и вставляешь туда эту команду:
```
git clone https://github.com/mesor21/OgreBeauty.git 
```

**<h2>IDE</h2>**

Что б вам было удобно работать с проектом, вам требуется скачать IDE IntelliJ IDEA

<Button primary link href="https://www.jetbrains.com/idea/download">
Ссылка на IntelliJ IDEA
</Button>

После открытия приложения у вас появится это окно

<picture>
  <img alt="Shows an illustrated sun in light mode and a moon with stars in dark mode." src="https://learn.microsoft.com/ru-ru/azure/hdinsight/spark/media/apache-spark-create-standalone-application/spark-1.png">
</picture>

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

<Button primary link href="https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token">
Создание токена
</Button>

Не знаю. Наверное все коммиты будут на ветке main


**<h2>Теория</h2>**
Прежде чем написать мне используем все ресурсы в этом и следующем пунке, а потом пишем мне.
* <Link href="https://habr.com/ru/post/474498/">Теория по JavaFX habl</Link> (прочитать всё кроме 7)
* <Link href="google.com">Google</Link>
* <Link href="https://t.me/RussiaChatGPTBot">ChatGPT на русском</Link>
* <Link href=""></Link>

Так как на ваших плечах будет лежать непростая задача:
Сделать фронт к приложению. Для решения этой задачи в вашем распоряжении будут service, controller и .fxml (ссылки на папки и файлы в следующем пункте).
В Main.java у вас есть метод start. Выставляйте параметры которые нужно.


<h4>Специально для Егора</h2>>
Пишем названия переменных понятным АНГЛИЙСКИМ ЯЗЫКОМ. Если не знаешь как назвать, вот тебе
<Button primary link href="https://translate.google.com/">
Гениальное решение проблемы Егора
</Button>

**<h2>С чем взаимодействовать</h2>**


1. <Link href="https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty">src/main/java/com/example/ogrebeauty</Link>
2. <Link href="https://github.com/mesor21/OgreBeauty/tree/main/src/main/resources/com/example/ogrebeauty">src/main/resources/com/example/ogrebeauty</Link>
   * <Link href="https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty/controller">controller</Link>
   * <Link href="https://github.com/mesor21/OgreBeauty/tree/main/src/main/java/com/example/ogrebeauty/service">service</Link>
   * <Link href="https://github.com/mesor21/OgreBeauty/blob/main/src/main/java/com/example/ogrebeauty/Main.java">Main.java</Link>

Советую ознакомится со всеми методами service так как взаимодействие со всеми данными у вас будет производится через service(обязательно посмотрите ServiceService). 
Желаю удачи!