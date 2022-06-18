package entityPackage.entities;

// Работа с конструктором. Используем котов.
public class Cat {

    // Чтобы выводить значения полей, нужно, чтобы они были public
    public int id = 0;
    public String name = "";
    public int age = 0;
    public String color = "";
    private String zalupka = "";

    // Всё известно о коте
    public Cat(int id, String name, Integer age, String color, String zalupka) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.zalupka = zalupka;
    }

    // У кота неизвестен возраст
    public Cat(int id, String name, String color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // Неизвестный кот, только с цветом
    public Cat(int id, String color){
        this.id = id;
        this.color = color;
    }

    // Метод для вывода информации об элементе структуры
    public void infoCat(){
        System.out.println("\tНомер кота: " + id);
        System.out.println("\tИмя: " + name);
        System.out.println("\tВозраст (лет): " + age);
        System.out.println("\tЦвет: " + color);
        System.out.println("\tZalupka: " + zalupka + "\n");
    }
}
