package entityPackage.entities;

import java.util.List;

// ������ � �������������. ���������� �����.
public class Cat {

    // ����� �������� �������� �����, �����, ����� ��� ���� public
    public int id = 0;
    public String name = "";
    public int age = 0;
    public String color = "";
    private String zalupka = "";
    private String home = "";

    // �� �������� � ����
    public Cat(int id, String name, Integer age, String color, String zalupka, String home) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.zalupka = zalupka;
        this.home = home;
    }

    // � ���� ���������� �������
    public Cat(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // ����������� ���, ������ � ������
    public Cat(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public Cat() {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.zalupka = zalupka;
        this.home = home;
    }

    // ����� ��� ������ ���������� �� �������� ���������
    public void infoCat() {
        System.out.println("\t����� ����: " + id);
        System.out.println("\t���: " + name);
        System.out.println("\t������� (���): " + age);
        System.out.println("\t����: " + color);
        System.out.println("\tZalupka: " + zalupka);
        System.out.println("\t���: " + home + "\n");
    }

    // ����� ��� ��������� ���� private zalupka
    public Cat changeZpInfo(Cat cn, String zp) {
        cn.zalupka = zp;
        return cn;
    }

    public Cat changeHomeInfo(Cat cn, String home) {
        cn.home = home;
        return cn;
    }

    public Cat catStr(Cat cn, String[] mass) {
        cn.id = Integer.parseInt(mass[0]);
        cn.name = mass[1].toUpperCase();
        cn.age = Integer.parseInt(mass[2]);
        cn.color = mass[3].repeat(2);
        cn.zalupka = mass[4];
        cn.home = mass[5].toLowerCase();
        return cn;
    }

    public Cat catList(Cat cn, List<String> lst) {
        cn.id = Integer.parseInt(lst.get(0));
        cn.name = lst.get(1);
        cn.age = Integer.parseInt(lst.get(2));
        cn.color = lst.get(3);
        cn.zalupka = lst.get(4);
        cn.home = lst.get(5);
        return cn;
    }
}
