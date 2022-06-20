package entityPackage;

import entityPackage.entities.Cat;

import java.util.*;

public class MyJava {
    // Переменные, которые открыты для дочерних классов
    public static int n1 = 5;
    public static String str1 = "this string";
    // Переменные, доступные в классах, в пределах пакета и в пределах классов-наследников
    protected static byte b1 = 12;
    protected static String str2 = "more strings";
    // Переменные, которые открыты только в классе MyJava, для обращения извне
    private static double d1 = 123.456;
    private static String str3 = "another string";

    // Классы-оболочек
    public static Integer interOb;
    private static Boolean blnOb;
    protected static Long lngOb;

    public static void main(String[] args) {
        System.out.println("\n\tHello\n\t\tworld!\n");

        // Объявление примитивов локальных переменных
        // Доступ - default = protected
        int num = 1;
        long number = 789456123;
        String name = "Примитив";
        boolean bool = false;
        System.out.println("Вывод локальных переменных: " + num + ", " + number + ", " + name + ", " + bool);

        System.out.println("\nВывод глобальных переменных public: " + MyJava.n1 + ", " + MyJava.str1);
        System.out.println("Вывод глобальных переменных protected: " + MyJava.b1 + ", " + MyJava.str2);
        System.out.println("Вывод глобальных переменных private: " + MyJava.d1 + ", " + MyJava.str3);

        // Использование классов-оболочек (упаковка)
        MyJava.interOb = num;
        MyJava.blnOb = bool;
        MyJava.lngOb = number;

        // Использование классов-оболочек (распаковка)
        int num2 = MyJava.interOb;
        boolean bool2 = MyJava.blnOb;
        long number2 = MyJava.lngOb;
        System.out.println("\nВывод переменных: " + num2 + ", " + bool2 + ", " + number2);

        // ===============================================================================
        System.out.println("\n\t====================\n");
        //Создаём наших котов
        Cat[] cats = new Cat[3];
        // Поле private позволяет себя заполнить
        Cat c1 = new Cat(1, "Боб", 5, "Чёрный", "Розовый", "Нет");
        cats[0] = c1;
        Cat c2 = new Cat(2, "Старый", "Серый");
        cats[1] = c2;
        Cat c3 = new Cat(3, "Трёхцветный");
        cats[2] = c3;

        //System.out.println("\nИнформация о котах:\n");
        for (int i = 0; i < cats.length; i++) {
            // cats[i].infoCat(); // Избавил от большого количества котов в консоли
            // System.out.println("Zalupka: " + с1.zalupka); // Поле private не позволяет себя вывести внутри другого класса
        }

        // Изменяем поля
        c1.name = "Марли";
        c1.age = 6;
        c3.name = "Брошенка";
        // c1.zalupka = "Красный";  // Поле private не даёт себя переназначить
        System.out.println("Изменили информацию о котах:\n");
        c1.infoCat();
        c3.infoCat();

        // Приравниваем двух котов
        System.out.println("Приравниваем двух котов и выводим нового:");
        Cat c4 = new Cat(4, "Рыжий");
        // с4 = c1; // Объекты не дают себя приравнять таким образом
        Cat c5 = c1;          // Объекты приравниваются полностью, даже поле private
        c5.id = 5;            // Подобным приравниванием мы делаем как бы указатель на c1, и при изменении поля в c5
        c5.name = "Бездатый"; // Оно так же изменится в c1
        c5.infoCat();
        c1.infoCat();

        // =========================================================================================================
        System.out.println("\n\t====================\n");
        // Изменяем поля с доступом private, используя метод
        c1.changeZpInfo(c1, "Красный");
        c1.infoCat();
        System.out.println("Информация о коте c3 до изменения: ");
        c3.infoCat();
        c3.changeHomeInfo(c3, "Грув стрит");
        System.out.println("Информация о коте c3 после изменения: ");
        c3.infoCat();
        c3.changeHomeInfo(c3, "Дом, милый дом");
        System.out.println("Информация о коте c3 после очередного изменения: ");
        c3.infoCat();
        // Делаем вывод: поле private можно изменить, если обращаться к нему, через метод, который находится в классе конструктора

        // Создаём нового кота, используя массив строк
        String[] m = {"6", "Шестёрка", "6", "Синий", "", "Где-то есть"};
        Cat c6 = new Cat();
        c6.catStr(c6, m);
        System.out.println("Кот, созданный через массив:");
        c6.infoCat();

        // Создаём нового кота, используя лист
        List<String> lst = Arrays.asList(m);
        lst.set(0, "7");
        lst.set(1, "Счастливчик");
        lst.set(3, "Красный");
        lst.set(5, "Отсутствует");
        Cat c7 = new Cat();
        c7.catList(c7, lst);
        System.out.println("Кот, созданный через лист:");
        c7.infoCat();

        // Работа с методами листа
        List<String> listA = new ArrayList<>();
        listA.add("elem1");
        listA.add("elem2");
        listA.add("elem3");
        List<String> listB = new ArrayList<>(Arrays.asList("elem4", "elem5", "elem6"));
        listA.addAll(listB);
        listA.remove(listA.indexOf("elem3")); // Удалили элемент "elem3" по его индексу
        listA.addAll(listA);
        Collections.sort(listA);
        listA.removeAll(listB);

        System.out.print("Содержимое листа: ");
        for (String l : listA) {
            System.out.print(l + " ");
        }
        System.out.println("\n");

        String strList = listA.toString();
        System.out.println("Строка из листа: " + strList);
    }
}
