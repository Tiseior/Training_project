package entityPackage.entities;

// ������ � �������������. ���������� �����.
public class Cat {

    // ����� �������� �������� �����, �����, ����� ��� ���� public
    public int id = 0;
    public String name = "";
    public int age = 0;
    public String color = "";
    private String zalupka = "";

    // �� �������� � ����
    public Cat(int id, String name, Integer age, String color, String zalupka) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
        this.zalupka = zalupka;
    }

    // � ���� ���������� �������
    public Cat(int id, String name, String color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    // ����������� ���, ������ � ������
    public Cat(int id, String color){
        this.id = id;
        this.color = color;
    }

    // ����� ��� ������ ���������� �� �������� ���������
    public void infoCat(){
        System.out.println("\t����� ����: " + id);
        System.out.println("\t���: " + name);
        System.out.println("\t������� (���): " + age);
        System.out.println("\t����: " + color);
        System.out.println("\tZalupka: " + zalupka + "\n");
    }
}
