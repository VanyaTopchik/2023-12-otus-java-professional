# 2023-12-otus-java-professional

Repository for homework from the course "Otus. Java Developer Professional"

Student: Golyshkin Ivan

#### Task 1 (hw01-gradle)
#### [Проект gradle с модульной структурой](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw01-gradle)
Описание/Пошаговая инструкция выполнения домашнего задания:

> 1. Создайте аккаунт на github.com (если еще нет)
> 2. Создайте репозиторий для домашних работ
> 3. Сделайте checkout репозитория на свой компьютер
> 4. Создайте локальный бранч hw01-gradle
> 5. Создать проект gradle
> 6. В проект добавьте последнюю версию зависимости com.google.guava:guava
> 7. Создайте модуль hw01-gradle
> 8. В модуле сделайте класс HelloOtus
> 9. В этом классе сделайте вызов какого-нибудь метода из guava
> 10. Создайте "толстый-jar"
> 11. Убедитесь, что "толстый-jar" запускается.
> 12. Сделайте pull-request в gitHub
> 13. Ссылку на PR отправьте на проверку (личный кабинет, чат с преподавателем).

#### Task 2 (hw02-generics)
#### [Применение коллекций](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw02-generics)
Описание/Пошаговая инструкция выполнения домашнего задания:

> Необходимо сделать todo в классах из пакета homework.
> Все тесты должны проходить.
> Предполагается использование встроенного в jdk функционала, поэтому реализация методов должна быть буквально из нескольких строк.

#### Task 3 (hw03-annotations)
#### [Свой тестовый фреймворк](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw03-annotations)
Описание/Пошаговая инструкция выполнения домашнего задания:

> Написать свой тестовый фреймворк.
> Поддержать свои аннотации @Test, @Before, @After.
> Запускать вызовом статического метода с именем класса с тестами.
> Т.е. надо сделать:
> 1. Создать три аннотации - @Test, @Before, @After.
> 2. Создать класс-тест, в котором будут методы, отмеченные аннотациями.
> 3. Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить методы отмеченные аннотациями и пункта 1.
> 4. Алгоритм запуска должен быть следующий::
> Метод(ы) Before
> Текущий метод Test
> Метод(ы) After
> Для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
> 5. Исключение в одном тесте не должно прерывать весь процесс тестирования.
> 6. На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло успешно, сколько упало, сколько было всего)
> 7. "Запускалка теста" не должна иметь состояние, но при этом весь функционал должен быть разбит на приватные методы.
> Надо придумать, как передавать информацию между методами.