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
> Предполагается использование встроенного в jdk функционала, поэтому реализация методов должна быть буквально из
> нескольких строк.

#### Task 3 (hw03-annotations)

#### [Свой тестовый фреймворк](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw03-annotations)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Написать свой тестовый фреймворк.  
> Поддержать свои аннотации @Test, @Before, @After.  
> Запускать вызовом статического метода с именем класса с тестами.  
> Т.е. надо сделать:
> 1. Создать три аннотации - @Test, @Before, @After.
> 2. Создать класс-тест, в котором будут методы, отмеченные аннотациями.
> 3. Создать "запускалку теста". На вход она должна получать имя класса с тестами, в котором следует найти и запустить
     методы отмеченные аннотациями и пункта 1.
> 4. Алгоритм запуска должен быть следующий::
     > Метод(ы) Before  
     > Текущий метод Test  
     > Метод(ы) After  
     > Для каждой такой "тройки" надо создать СВОЙ объект класса-теста.
> 5. Исключение в одном тесте не должно прерывать весь процесс тестирования.
> 6. На основании возникших во время тестирования исключений вывести статистику выполнения тестов (сколько прошло
     успешно, сколько упало, сколько было всего)
> 7. "Запускалка теста" не должна иметь состояние, но при этом весь функционал должен быть разбит на приватные методы.
     > Надо придумать, как передавать информацию между методами.

#### Task 4 (hw04-gc)

#### [Определение нужного размера хипа](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw04-gc)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Есть готовое приложение.  
> Запустите его с размером хипа 256 Мб и посмотрите в логе время выполнения.  
> Пример вывода: spend msec:18284, sec:18  
> Увеличьте размер хипа до 2Гб, замерьте.  
> Время выполнения запусков записывайте в таблицу.  
> Определите оптимальный размер хипа, т.е. размер, превышение которого, не приводит к сокращению времени выполнения
> приложения.  
> Оптимизируйте работу приложения.  
> Т.е. не меняя логики работы (но изменяя код), сделайте так, чтобы приложение работало быстро с минимальным хипом.  
> Повторите измерения времени выполнения программы для тех же значений размера хипа.

#### Task 5 (hw05-aop)

#### [Автоматическое логирование](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw05-aop)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Разработайте такой функционал:  
> метод класса можно пометить самодельной аннотацией @Log, например, так:

```
class TestLogging implements TestLoggingInterface {
    @Log
    public void calculation(int param) {
    }
}
```

> При вызове этого метода "автомагически" в консоль должны логироваться значения параметров.  
> Например так.

```
class Demo {
    public void action() {
        new TestLogging().calculation(6);
    }
}
```

> В консоле дожно быть: executed method: calculation, param: 6  
> Обратите внимание: явного вызова логирования быть не должно.  
> Учтите, что аннотацию можно поставить, например, на такие методы:

```
    public void calculation(int param1)
    public void calculation(int param1, int param2)
    public void calculation(int param1, int param2, String param3)
```

#### Task 6 (hw06-solid)

#### [Эмулятор банкомата](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw06-solid)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Написать эмулятор АТМ (банкомата).  
> Объект класса АТМ должен уметь:
> - Принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
> - Выдавать запрошенную сумму минимальным количеством банкнот или ошибку, если сумму нельзя выдать.
    Это задание не на алгоритмы, а на проектирование.
    Поэтому оптимизировать выдачу не надо.
> - Выдавать сумму остатка денежных средств
    В этом задании больше думайте об архитектуре приложения.
    Не отвлекайтесь на создание таких объектов как: пользователь, авторизация, клавиатура, дисплей, UI (консольный, Web,
    Swing), валюта, счет, карта, т.д.
    Все это не только не нужно, но и вредно!

#### Task 7 (hw07-patterns)

#### [Обработчик сообщений](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw07-patterns)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Реализовать todo из модуля homework.

#### Task 8 (hw08-serialization)

#### [Обработчик json-ов](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw08-serialization)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Некая система:
> - принимает входящий json файл;
> - обрабатывает данные из файла;
> - формирует ответный файл.  
> Нужно реализовать недостающий функционал

#### Task 9 (hw09-jdbc)

#### [Самодельный ORM](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw09-jdbc)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Работа должна использовать базу данных в docker-контейнере .
В модуле homework реализуйте классы:
> - EntityClassMetaData
> - EntitySQLMetaData
> - DataTemplateJdbc
> - Метод main в классе HomeWork должен работать без ошибок.

#### Task 10 (hw10-hibernate)

#### [Использование Hibernate](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw10-hibernate)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Работа должна использовать базу данных в docker-контейнере.  
> За основу возьмите пример из вебинара про JPQL (class DbServiceDemo).  
> Добавьте в Client поля:  
> - адрес (OneToOne)  
> ```
> class Address {  
>   private String street;  
> }
> ```  
> - телефон (OneToMany)  
> ```
> class Phone {  
>   private String number;  
> }  
> ```
> Разметьте классы таким образом, чтобы при сохранении/чтении объека Client каскадно сохранялись/читались вложенные объекты.  
> 
> ВАЖНО.
> Hibernate должен создать только три таблицы: для телефонов, адресов и клиентов.  
> При сохранении нового объекта не должно быть update-ов.  
> Посмотрите в логи и проверьте, что эти два требования выполняются.  

#### Task 15 (hw09-jdbc)

#### [Последовательность чисел](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw15-concurrency)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Два потока печатают числа от 1 до 10, потом от 10 до 1.  
Надо сделать так, чтобы числа чередовались, т.е. получился такой вывод:  
Поток 1:1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3 4....  
Поток 2: 1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3....  
Всегда должен начинать Поток 1.  
