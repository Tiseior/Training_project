package entityPackage;

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

    // Классы-оболочкек
    public static Integer inter_ob;
    private static Boolean bln_ob;
    protected static Long lng_ob;

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
        MyJava.inter_ob = num;
        MyJava.bln_ob = bool;
        MyJava.lng_ob = number;

        // Использование классов-оболочек (распаковка)
        int num2 = MyJava.inter_ob;
        boolean bool2 = MyJava.bln_ob;
        long number2 = MyJava.lng_ob;
        System.out.println("\nВывод переменных: " + num2 + ", " + bool2 + ", " + number2);
    }
}
