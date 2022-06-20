package entityPackage.entities;

import java.util.List;

// Работа с конструктором. Используем котов.
public class Cat {

    // Чтобы выводить значения полей, нужно, чтобы они были public
    public int id = 0;
    public String name = "";
    public int age = 0;
    public String color = "";
    private String zalupka = "";
    private String home = "";

    // Всё известно о коте
    public Cat(int id, String name, Integer age, String color, String zalupka, String home) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.zalupka = zalupka;
        this.home = home;
    }

    // У кота неизвестен возраст
    public Cat(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // Неизвестный кот, только с цветом
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

    // Метод для вывода информации об элементе структуры
    public void infoCat() {
        System.out.println("\tНомер кота: " + id);
        System.out.println("\tИмя: " + name);
        System.out.println("\tВозраст (лет): " + age);
        System.out.println("\tЦвет: " + color);
        System.out.println("\tZalupka: " + zalupka);
        System.out.println("\tДом: " + home + "\n");
    }

    // Метод для изменения поля private zalupka
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
