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
> В модуле homework реализуйте классы:
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
> Разметьте классы таким образом, чтобы при сохранении/чтении объека Client каскадно сохранялись/читались вложенные
> объекты.
>
> ВАЖНО.
> Hibernate должен создать только три таблицы: для телефонов, адресов и клиентов.  
> При сохранении нового объекта не должно быть update-ов.  
> Посмотрите в логи и проверьте, что эти два требования выполняются.

#### Task 11 (hw11-cache)

#### [Свой cache engine](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw11-cache)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Закончите реализацию MyCache из вебинара.  
> Используйте WeakHashMap для хранения значений.  
> Добавьте кэширование в DBService из задания про Hibernate ORM или "Самодельный ORM".  
> Для простоты скопируйте нужные классы в это ДЗ.  
> Убедитесь, что ваш кэш действительно работает быстрее СУБД и сбрасывается при недостатке памяти.

#### Task 12 (hw12-webserver)

#### [Веб сервер](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw12-webserver)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Встроить веб-сервер в приложение из ДЗ про Hibernate ORM (или в пример из вебинара встроить ДЗ про Hibernate :))
> Сделать стартовую страницу, на которой админ должен аутентифицироваться;
> Сделать админскую страницу для работы с клиентами;
> На этой странице должны быть доступны следующие функции:
> - создать клиента
> - получить список клиентов.

#### Task 13 (hw13-di)

#### [Собственный IoC контейнер](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw13-di)

Описание/Пошаговая инструкция выполнения домашнего задания:

> - Скачать заготовку приложения тренажера таблицы умножения из репозитория с примерами
> - В классе AppComponentsContainerImpl реализовать обработку, полученную в конструкторе конфигурации, основываясь на разметке аннотациями из пакета appcontainer.
> Так же необходимо реализовать методы getAppComponent
> - В итоге должно получиться работающее приложение. Менять можно только класс AppComponentsContainerImpl
> Дополнительное задание (можно не делать):
> - Разделить AppConfig на несколько классов и распределить по ним создание компонентов. В AppComponentsContainerImpl добавить конструктор, который обрабатывает несколько классов-конфигураций
> Дополнительное задание (можно не делать):
> - В AppComponentsContainerImpl добавить конструктор, который принимает на вход имя пакета, и обрабатывает все имеющиеся там классы-конфигурации (см. зависимости в pom.xml)

#### Task 14 (hw14-springboot)

#### [Веб-приложение на Spring Boot](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw14-springboot)

Описание/Пошаговая инструкция выполнения домашнего задания:

> - Взять за основу ДЗ к вебинару Занятие «Web сервер. ДЗ», но без страницы логина;
> - Вместо Jetty использовать Spring Boot;
> - Работу с базой данных реализовать на Spring Data Jdbc;
> - В качестве движка шаблонов использовать Thymeleaf;
> - Если Thymeleaf не нравится, используйте чистый HTML и JavaScript. Авторизацию и аутентификацию делать не надо.

#### Task 15 (hw15-concurrency)

#### [Последовательность чисел](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw15-concurrency)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Два потока печатают числа от 1 до 10, потом от 10 до 1.  
> Надо сделать так, чтобы числа чередовались, т.е. получился такой вывод:  
> Поток 1:1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3 4....  
> Поток 2: 1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1 2 3....  
> Всегда должен начинать Поток 1.  

#### Task 16 (hw16-queues)

#### [Очереди](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw16-queues)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Реализовать SensorDataProcessorBuffered из примера к вебинару, представляющий из себя класс,
> который накапливает внутри себя данные, в порядке времени измерения, а при достижении заданного размера буфера
> выполняет запись этого буфера с помощью метода flush и зависимости SensorDataBufferedWriter.  
> Тесты внутри SensorDataProcessorBufferedTest должны проходить

#### Task 17 (hw17-grpc)

#### [grpc](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw17-grpc)

Описание/Пошаговая инструкция выполнения домашнего задания:

> Разработать клиент-серверное приложение с применением технологии gRPC.
> 
> - Серверная часть.
> Cервер по запросу клиента генерирует последовательность чисел.    
> Pапрос от клиента содержит начальное значение (firstValue) и конечное(lastValue). 
> Раз в две секунды сервер генерирует новое значение и "стримит" его клиенту:   
> firstValue + 1    
> firstValue + 2    
> ...   
> lastValue 

> - Клиентская часть.
> Клиент отправдяет запрос серверу для получения последовательности чисел от 0 до 30.   
> Клиент запускает цикл от 0 до 50. 
> Раз в секунду выводит в консоль число (currentValue) по такой формуле:    
> currentValue = [currentValue] + [ПОСЛЕДНЕЕ число от сервера] + 1  
> Начальное значение: currentValue = 0  
> Число, полученное от сервера должно учитываться только один раз.  
> Обратите внимание, сервер может вернуть несколько чисел, надо взять именно ПОСЛЕДНЕЕ. 
> 
> Должно получиться примерно так:   
> currentValue:1    
> число от сервера:2    
> currentValue:4 <--- число от сервера учитываем только один раз    
> currentValue:5 <--- тут число от сервера уже не учитывается.  
> число от сервера:3    
> currentValue:9    
> currentValue:10   
> new value:4   
> currentValue:15   
> currentValue:16   
> 
> - Для коммуникации используйте gRPC.    
> Клиент и сервер не обязательно разделять по модулям.  
> Можно сделать один модуль с двумя main-классами для клиента и сервера.   
> 
> Пример лога работы клиента (new value - это значение полученное от сервера):  
> 21:44:04.782 [main] INFO ru.otus.numbers.client.NumbersClient - numbers Client is starting... 
> 21:44:04.932 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:1    
> 21:44:05.140 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:2   
> 21:44:05.933 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:4    
> 21:44:06.933 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:5    
> 21:44:07.113 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:3   
> 21:44:07.934 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:9    
> 21:44:08.934 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:10   
> 21:44:09.112 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:4   
> 21:44:09.935 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:15   
> 21:44:10.935 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:16   
> 21:44:11.113 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:5   
> 21:44:11.935 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:22   
> 21:44:12.936 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:23   
> 21:44:13.113 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:6   
> 21:44:13.936 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:30   
> 21:44:14.937 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:31   
> 21:44:15.114 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:7   
> 21:44:15.938 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:39   
> 21:44:16.938 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:40   
> 21:44:17.113 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:8   
> 21:44:17.939 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:49   
> 21:44:18.939 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:50   
> 21:44:19.113 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:9   
> 21:44:19.940 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:60   
> 21:44:20.940 [main] INFO ru.otus.numbers.client.NumbersClient - currentValue:61   
> 21:44:21.114 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - new value:10  
> 21:44:21.119 [grpc-default-executor-0] INFO r.o.n.client.ClientStreamObserver - request completed 

#### Task 18 (hw18-webflux)

#### [WebFlux](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/hw18-webflux)

Описание/Пошаговая инструкция выполнения домашнего задания:

> В чат из примера к вебинару добавьте специальную комнату: 1408.   
> В этой комнате нельзя отправлять сообщения.   
> Однако в нее приходят все сообщения из всех других комнат.   
> Обратите внимание: при входе в комнату 1408 должны загрузиться все сообщения из всех комнат (по аналогии с типовыми комнатами).

#### Final project (project)

#### [Финальный проект](https://github.com/VanyaTopchik/2023-12-otus-java-professional/tree/main/project)

Описание:

## Запуск
Поднятие базы данных Postgres:
```
cd docker
docker-compose up
```
Запуск приложения Spring Boot:
```
gradle bootRun
```
## API
Получение списка вакансий по названию (параметр `title` не обязателен)
```
http://localhost:8080/vacancy?title=Грузчик
```
Главная страница
```
http://localhost:8080
```
Страница создания вакансии
```
http://localhost:8080/add
```
Страница редактирования вакансии
```
http://localhost:8080/edit/{vacancy_id}
```
