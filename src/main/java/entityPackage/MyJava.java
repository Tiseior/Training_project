package entityPackage;

public class MyJava {
    // ����������, ������� ������� ��� �������� �������
    public static int n1 = 5;
    public static String str1 = "this string";
    // ����������, ��������� � �������, � �������� ������ � � �������� �������-�����������
    protected static byte b1 = 12;
    protected static String str2 = "more strings";
    // ����������, ������� ������� ������ � ������ MyJava, ��� ��������� �����
    private static double d1 = 123.456;
    private static String str3 = "another string";

    // ������-���������
    public static Integer inter_ob;
    private static Boolean bln_ob;
    protected static Long lng_ob;

    public static void main(String[] args) {
        System.out.println("\n\tHello\n\t\tworld!\n");

        // ���������� ���������� ��������� ����������
        // ������ - default = protected
        int num = 1;
        long number = 789456123;
        String name = "��������";
        boolean bool = false;
        System.out.println("����� ��������� ����������: " + num + ", " + number + ", " + name + ", " + bool);

        System.out.println("\n����� ���������� ���������� public: " + MyJava.n1 + ", " + MyJava.str1);
        System.out.println("����� ���������� ���������� protected: " + MyJava.b1 + ", " + MyJava.str2);
        System.out.println("����� ���������� ���������� private: " + MyJava.d1 + ", " + MyJava.str3);

        // ������������� �������-�������� (��������)
        MyJava.inter_ob = num;
        MyJava.bln_ob = bool;
        MyJava.lng_ob = number;

        // ������������� �������-�������� (����������)
        int num2 = MyJava.inter_ob;
        boolean bool2 = MyJava.bln_ob;
        long number2 = MyJava.lng_ob;
        System.out.println("\n����� ����������: " + num2 + ", " + bool2 + ", " + number2);
    }
}
