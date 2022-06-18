package entityPackage;

import entityPackage.entities.Cat;

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

    // ������-��������
    public static Integer interOb;
    private static Boolean blnOb;
    protected static Long lngOb;

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
        MyJava.interOb = num;
        MyJava.blnOb = bool;
        MyJava.lngOb = number;

        // ������������� �������-�������� (����������)
        int num2 = MyJava.interOb;
        boolean bool2 = MyJava.blnOb;
        long number2 = MyJava.lngOb;
        System.out.println("\n����� ����������: " + num2 + ", " + bool2 + ", " + number2);

        // ===============================================================================
        //������ ����� �����
        Cat[] cats = new Cat[3];
        // ���� private ��������� ���� ���������
        Cat c1 = new Cat(1, "���", 5, "׸����", "�������");
        cats[0] = c1;
        Cat c2 = new Cat(2, "������", "�����");
        cats[1] = c2;
        Cat c3 = new Cat(3, "����������");
        cats[2] = c3;

        System.out.println("\n���������� � �����:\n");
        for (int i = 0; i < cats.length; i++) {
            cats[i].infoCat();
            // System.out.println("Zalupka: " + �1.zalupka); // ���� private �� ��������� ���� ������� ������ ������� ������
        }

        // �������� ����
        c1.name = "�����";
        c1.age = 6;
        c3.name = "��������";
        // c1.zalupka = "�������";  // ���� private �� ��� ���� �������������
        System.out.println("�������� ���������� � �����:\n");
        c1.infoCat();
        c3.infoCat();

        // ������������ ���� �����
        System.out.println("������������ ���� ����� � ������� ������:");
        Cat c4 = new Cat(4, "�����");
        // �4 = c1; // ������� �� ���� ���� ���������� ����� �������
        Cat c5 = c1;          // ������� �������������� ���������, ���� ���� private
        c5.id = 5;            // �������� �������������� �� ������ ��� �� ��������� �� c1, � ��� ��������� ���� � c5
        c5.name = "��������"; // ��� ��� �� ��������� � c1
        c5.infoCat();
        c1.infoCat();
    }
}
