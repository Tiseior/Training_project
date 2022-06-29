package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entitiesCreate.CreateAbstractObject;

import java.util.*;
import java.util.stream.Collectors;

public class MyJava3 {

    // ������� �� Jira, ���� 2

    public static void main(String[] args) {

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        System.out.println("���������� ������������ ����:");
        watchListAbstract(list, 0);
        System.out.println("��� id: ");
        watchListAbstractId(list);
        System.out.println("0. ������� ������� ������ � ������� id �� ����� 1: ");
        List<AbstractObject> filteredList = list.stream().filter(e -> e.id != 1).toList();
        watchListAbstractId(filteredList);

        List<Integer> mappedList = list.stream().map(e -> {
            return e.id;
        }).toList(); // ������ ������ ��� ���������, �� ���� ����� �������?

        mappedList = list.stream().map(e -> {
            if(e.abstractObjectList.size() != 0){
                return e.id;
            } else {
                return null;
            }
        }).toList(); // �������� �� breakpoint`��: �������� ���� � null. ��� ������? ������� ������������� �� �������, ����� ���������

        mappedList = list.stream().filter(
                filteredObject -> (filteredObject.abstractObjectList.size() != 0)
        ).toList().stream().map(
                mappedObject -> mappedObject.id
        ).toList(); // ������ ����

        System.out.println("1. ������� ������� ������ � ������� id - ���������� �����: ");
        filteredList = list.stream().filter(e -> (e.id > 9 && e.id < 100)).toList();
        watchListAbstractId(filteredList);

        System.out.println("2. ������� ������� ������ � ������� name �������� �����: ");
        // ������������� ����������� ���������. (.) - ����� ������, (*) - 0 ��� ����� ���, (\\d) - ����� � ��������������
        filteredList = list.stream().filter(e -> e.name.matches(".*\\d.*")).toList();
        watchListAbstractName(filteredList);

        System.out.println("3. ������� ������� ������ � ������� isAlright=false: ");
        filteredList = list.stream().filter(e -> e.isAlright == false).toList();
        watchListAbstractName(filteredList);

        System.out.println("4. ������� ������� ������ � �������� stringList, abstractObjectList: ");
        System.out.println("\t�������� stringList: ");
        filteredList = list.stream().filter(e -> e.stringList.size() != 0).toList();
        watchListAbstractName(filteredList);
        System.out.println("\t�������� abstractObjectList: ");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() != 0).toList();
        watchListAbstractName(filteredList);
        System.out.println("\t�������� stringList � abstractObjectList: ");
        filteredList = list.stream().filter(e -> (e.stringList.size() != 0 && e.abstractObjectList.size() != 0)).toList();
        watchListAbstractName(filteredList);

        System.out.println("5. ������� ������� ������, � ������� � stringList 2 �������: ");
        filteredList = list.stream().filter(e -> e.stringList.size() == 2).toList();
        watchListAbstractName(filteredList);

        System.out.println("6. ������� ������� ������, ���������� � stringList ������ 'two': ");
        filteredList = list.stream().filter(e -> e.stringList.contains("two")).toList();
        watchListAbstractName(filteredList);

        System.out.println("7. ������� ������� ������, ���������� �� ��������� ����������� abstractObject name - 'mistake': ");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() > 0 &&
                e.abstractObjectList.stream().filter(n -> n.name == "mistake").toList().size() != 0).toList();
        watchListAbstractName(filteredList);

        System.out.println("8. ������� ������� ������, � ������� ����������� ���� �� ����� ���� (� ��������� ���� ����������, � ���������� ����� ���� ���� ����������):");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() != 0 &&
                e.abstractObjectList.stream().filter(n -> n.abstractObjectList.size() != 0).toList().size() != 0).toList();
        watchListAbstractName(filteredList);

        System.out.println("+9. ������������� ��� ������� ������� ������ �� �� id:");
        filteredList = list.stream().sorted(Comparator.comparingInt(e -> e.id)).toList();
        watchListAbstractId(filteredList);

        System.out.println("+10. ������� ������ ���������� id �������� ������� ������:");
        Map<Integer, List<AbstractObject>> listMap = list.stream().collect(Collectors.groupingBy(AbstractObject::getId));
        filteredList = list.stream().filter(e -> listMap.get(e.id).size() == 1).toList();
        watchListAbstractId(filteredList);

        System.out.println("+11. ������� ��� id �������� ������� ������ ��� ����������:");
        Set<Integer> listSet = new HashSet<>();
        filteredList = list.stream().filter(e -> listSet.add(e.id)).collect(Collectors.toSet()).stream().toList();
        watchListAbstractId(filteredList);
    }

    public static void watchListAbstract(List<AbstractObject> list, int count) {
        for (AbstractObject lst : list) {
            System.out.println("\n" + ("\t").repeat(count) + "Id: " + lst.id);
            System.out.println(("\t").repeat(count) + "Count: " + lst.count);
            System.out.println(("\t").repeat(count) + "Name: " + lst.name);
            System.out.println(("\t").repeat(count) + "StringList: ");
            System.out.print(("\t").repeat(count));
            for (String str : lst.stringList) {
                System.out.println(("\t").repeat(count + 1) + str);
                System.out.print(("\t").repeat(count));
            }
            System.out.println("IsAlright: " + lst.isAlright);
            System.out.println(("\t").repeat(count) + "AbstractObjectList: " + lst.abstractObjectList.size());
            if (lst.abstractObjectList.size() != 0) {
                watchListAbstract(lst.abstractObjectList, ++count);
                --count;
            }
        }
    }

    public static void watchListAbstractId(List<AbstractObject> list) {
        System.out.print("\t");
        list.stream().forEach(obj -> {
            System.out.print(obj.id + " ");
        });
        System.out.println("\n");
    }

    public static void watchListAbstractName(List<AbstractObject> list) {
        System.out.print("\t");
        list.stream().forEach(obj -> {
            System.out.print(obj.name + " ");
        });
        System.out.println("\n");
    }
}
