package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entitiesCreate.CreateAbstractObject;

import java.util.List;

public class MyJava3 {

    // Задачки из Jira, этап 2

    public static void main(String[] args) {

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        System.out.println("Рассмотрим получившийся лист:\n");
        watchListAbstract(list);
        System.out.println("Все id: ");
        watchListAbstractId(list);
        System.out.println("0. Объекты первого уровня у которых id не равен 1: ");
        List<AbstractObject> filteredList = list.stream().filter(e -> e.id != 1).toList();
        watchListAbstractId(filteredList);

        System.out.println("1. Объекты первого уровня у которых id - двузначное число: ");
        filteredList = list.stream().filter(e -> (e.id > 9 && e.id < 100)).toList();
        watchListAbstractId(filteredList);

        System.out.println("2. Объекты первого уровня у которых name содержит числа: ");
        // Использование регулярного выражение. (.) - любой символ, (*) - 0 или более раз, (\\d) - цифра с экранированием
        filteredList = list.stream().filter(e -> e.name.matches(".*\\d.*")).toList();
        watchListAbstractName(filteredList);

        System.out.println("3. Объекты первого уровня у которых isAlright=false: ");
        filteredList = list.stream().filter(e -> e.isAlright == false).toList();
        watchListAbstractName(filteredList);

        System.out.println("4. Объекты первого уровня с непустым stringList, abstractObjectList: ");
        System.out.println("\tНепустой stringList: ");
        filteredList = list.stream().filter(e -> e.stringList.size() != 0).toList();
        watchListAbstractName(filteredList);
        System.out.println("\tНепустой abstractObjectList: ");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() != 0).toList();
        watchListAbstractName(filteredList);
        System.out.println("\tНепустой stringList и abstractObjectList: ");
        filteredList = list.stream().filter(e -> (e.stringList.size() != 0 && e.abstractObjectList.size() != 0)).toList();
        watchListAbstractName(filteredList);

        System.out.println("5. Объекты первого уровня, у которых в stringList 2 объекта: ");
        filteredList = list.stream().filter(e -> e.stringList.size() == 2).toList();
        watchListAbstractName(filteredList);

        System.out.println("6. Объекты первого уровня, содержащие в stringList объект 'two': ");
        filteredList = list.stream().filter(e -> e.stringList.indexOf("two") != -1).toList();
        watchListAbstractName(filteredList);

        System.out.println("7. Объекты первого уровня, содержащие во вложенных экземплярах abstractObject name - 'mistake': ");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() > 0 &&
                e.abstractObjectList.stream().filter(n -> n.name == "mistake").toList().size() != 0).toList();
        watchListAbstractName(filteredList);

        System.out.println("8. Объекты первого уровня, у которых вложенность была бы равна трем (в абстракте лист абстрактов, в экземпляре листа тоже лист абстрактов)");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() != 0 &&
                e.abstractObjectList.stream().filter(n -> n.abstractObjectList.size() != 0).toList().size() != 0).toList();
        watchListAbstractName(filteredList);
    }

    // Какой-то вывод абстрактных объектов, но он весьма кривой
    public static void watchListAbstract(List<AbstractObject> list) {
        for (AbstractObject lst : list) {
            System.out.println("\tId: " + lst.id);
            System.out.println("\tCount: " + lst.count);
            System.out.println("\tName: " + lst.name);
            System.out.println("\tStringList: ");
            System.out.print("\t");
            for (String str : lst.stringList) {
                System.out.println("\t" + str);
                System.out.print("\t");
            }
            System.out.println("IsAlright: " + lst.isAlright);
            System.out.println("\tAbstractObjectList: " + lst.abstractObjectList.size());
            if (lst.abstractObjectList.size() != 0) {
                System.out.println("");
                watchListAbstract(lst.abstractObjectList);
            } else {
                System.out.println(" ");
            }
            System.out.println("");
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
