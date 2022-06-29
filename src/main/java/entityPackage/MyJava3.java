package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entitiesCreate.CreateAbstractObject;

import java.util.*;
import java.util.stream.Collectors;

public class MyJava3 {

    // Задачки из Jira, этап 2

    public static void main(String[] args) {

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        System.out.println("Рассмотрим получившийся лист:");
        watchListAbstract(list, 0);
        System.out.println("\nВсе id: ");
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

        System.out.println("4. Объекты первого уровня с непустым stringList, abstractObjectList:\n");
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
        filteredList = list.stream().filter(e -> e.stringList.contains("two")).toList();
        watchListAbstractName(filteredList);

        System.out.println("7. Объекты первого уровня, содержащие во вложенных экземплярах abstractObject name - 'mistake': ");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() > 0 &&
                e.abstractObjectList.stream().filter(n -> n.name == "mistake").toList().size() != 0).toList();
        watchListAbstractName(filteredList);

        System.out.println("8. Объекты первого уровня, у которых вложенность была бы равна трем (в абстракте лист абстрактов, в экземпляре листа тоже лист абстрактов):");
        filteredList = list.stream().filter(e -> e.abstractObjectList.size() != 0 &&
                e.abstractObjectList.stream().filter(n -> n.abstractObjectList.size() != 0).toList().size() != 0).toList();
        watchListAbstractName(filteredList);

        System.out.println("+9. Отсортировать все объекты первого уровня по их id:");
        filteredList = list.stream().sorted(Comparator.comparingInt(e -> e.id)).toList();
        watchListAbstractId(filteredList);

        System.out.println("+10. Вывести только уникальные id объектов первого уровня:");
        Map<Integer, List<AbstractObject>> listMap = list.stream().collect(Collectors.groupingBy(AbstractObject::getId));
        filteredList = list.stream().filter(e -> listMap.get(e.id).size() == 1).toList();
        watchListAbstractId(filteredList);

        System.out.println("+11. Вывести все id объектов первого уровня без повторений:");
        Set<Integer> listSet = new HashSet<>();
        filteredList = list.stream().filter(e -> listSet.add(e.id)).collect(Collectors.toSet()).stream().toList();
        watchListAbstractId(filteredList);
        // =======================================================================================================

        System.out.println("Задачи stream().map\n");
        List<Integer> mappedList = list.stream().map(e -> {
            return e.id;
        }).toList(); // просто вывели все айдишники, но если нужно условие?

        mappedList = list.stream().map(e -> {
            if (e.id < 10) {
                return e.id;
            } else {
                return null;
            }
        }).toList();  // Если нужно условие, то можно прописать if в {}
        System.out.println("MappedList: " + mappedList);
        /*mappedList = list.stream().filter(e -> e.id < 10).map(e -> {
            return e.id;
        }).toList();*/
        mappedList = list.stream().filter(e -> e.id < 10).map(e -> e.id).toList();
        System.out.println("New MappedList: " + mappedList + "\n");
        // В начале я подумал, что это задание, а ты мне примеры показывал))

        mappedList = list.stream().map(e -> {
            if (e.abstractObjectList.size() != 0) {
                return e.id;
            } else {
                return null;
            }
        }).toList(); // посмотри по breakpoint`ам: получаем лист с null. Как обойти? Сначала отфильтровать по условию, затем отмаппить

        mappedList = list.stream().filter(
                filteredObject -> (filteredObject.abstractObjectList.size() != 0)
        ).toList().stream().map(
                mappedObject -> mappedObject.id
        ).toList(); // теперь норм
        System.out.println("Твой норм способ MappedList: " + mappedList);
        mappedList = list.stream().filter(filteredObject -> filteredObject.abstractObjectList.size() != 0).map(
                mappedObject -> mappedObject.id).toList();
        System.out.println("Мой норм способ MappedList: " + mappedList + "\n");
        // Есть какая-то существенная разница?

        System.out.println("1. Вывести все count объектов первого, второго, третьего уровней по отдельности и вместе:\n");
        System.out.println("\tВсе count объектов первого уровня: ");
        mappedList = list.stream().map(e -> e.count).toList();
        System.out.println("\t" + mappedList + "\n");
        System.out.println("\tВсе count объектов второго уровня: ");
        mappedList = list.stream().filter(e -> e.abstractObjectList.size() != 0).flatMap(
                el -> el.abstractObjectList.stream()).map(e -> e.count).toList();
        System.out.println("\t" + mappedList + "\n");
        System.out.println("\tВсе count объектов третьего уровня: ");
        // В предыдущем разделе, в 8 задании, где тоже нужно было углубиться на третий уровень я проверял,
        // есть ли вообще возможность куда-то углубиться, но тут решил убрать
        mappedList = list.stream().filter(e -> e.abstractObjectList.stream().filter(
                n -> n.abstractObjectList.size() != 0).toList().size() != 0).flatMap(
                e -> e.abstractObjectList.stream().flatMap(n -> n.abstractObjectList.stream())).map(e -> e.count).toList();
        System.out.println("\t" + mappedList + "\n");

        System.out.println("2. Вывести все name, если id объекта больше 5. Для объектов первого уровня:");
        List<String> mappedListString = list.stream().filter(e -> e.id > 5).map(el -> el.name).toList();
        System.out.println("\t" + mappedListString + "\n");

        System.out.println("3. Вывести все name второго уровня, если id объекта первого уровня меньше 10, а id второго уровня меньше 100:");
        mappedListString = list.stream().filter(e -> e.id < 10).flatMap(
                el -> el.abstractObjectList.stream()).filter(e -> e.id < 100).map(el -> el.name).toList();
        System.out.println("\t" + mappedListString + "\n");

        System.out.println("4. Вывести все isAlright для объектов второго уровня и подсчитать, чего больше - тру или фолс:");
        List<Boolean> mappedListBool = list.stream().filter(e -> e.abstractObjectList.size() != 0).flatMap(
                el -> el.abstractObjectList.stream()).map(e -> e.isAlright).toList();
        System.out.println("\t" + mappedListBool + "\n");
        if(mappedListBool.stream().filter(e -> e.booleanValue()).count() > mappedListBool.stream().filter(e -> !e.booleanValue()).count()){
            System.out.println("Больше элементов true\n");
        } else {
            System.out.println("Больше элементов false\n");
        }
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
