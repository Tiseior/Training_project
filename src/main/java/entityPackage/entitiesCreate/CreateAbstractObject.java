package entityPackage.entitiesCreate;

import entityPackage.entities.AbstractObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAbstractObject {

    public List<AbstractObject> createListOfAbstractObjects() {
        // Создаётся итоговый лист. В него добавляется элемент, в котором содержатся два пустых листа.
        List<AbstractObject> resultList = new ArrayList<>();
        resultList.add(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true));

        // Создаётся лист объектов с одним абстрактным элементом, в котором содержатся два пустых листа.
        // В итоговый лист добавляется новый элемент, в котором есть лист с одним элементом и ранее созданный лист объектов.
        List<AbstractObject> objectsList = Arrays.asList(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true));
        resultList.add(new AbstractObject(2, 32, "anyway", Arrays.asList("one"), objectsList, true));

        // Изменяется содержимое листа объектов. Теперь там 3 элемента. В первом элементе объект, содержащий 2 пустых листа.
        // Во втором объекте лист с четырьмя элементами и пустой лист. В третьем объекте лист с двумя элементами и лист
        // с объектом, в котором 2 пустых листа.
        // В итоговый лист добавляется новый объект, содержащий лист из двух элементов и весь ранее изменённый лист объектов.
        objectsList = Arrays.asList(new AbstractObject(13, -2, "name", new ArrayList<>(), new ArrayList<>(), true),
                new AbstractObject(11, 2223, "not", Arrays.asList("one", "two", "three", "four"), new ArrayList<>(), false),
                new AbstractObject(4, 23, "inside", Arrays.asList("one", "two"), Arrays.asList(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true)), false));
        resultList.add(new AbstractObject(1, 32, "input", Arrays.asList("one", "two", "three"), objectsList, false));

        // В итоговый лист добавляется лист с тремя элементами и пустым листом.
        resultList.add(new AbstractObject(3, 32, "23anywhere", Arrays.asList("one", "two", "three", "four"), new ArrayList<>(), true));

        // Изменяется содержимое листа объектов. Теперь там 2 элемента. В первом элементе объект с листом из одного элемента и пустой лист.
        // Во втором элементе так же два листа, первый с одним элементом, во второй пустой.
        // Добавляется новый элемент в итоговый лист. В новом объекте лист с четырьмя элементами и изменённый лист объектов.
        objectsList = Arrays.asList(new AbstractObject(1, 1, "name", Arrays.asList("one"), new ArrayList<>(), true),
                new AbstractObject(2, 2, "23", Arrays.asList("one"), new ArrayList<>(), false));
        resultList.add(new AbstractObject(15, 32, "sometimes", Arrays.asList("one", "two", "three", "four"), objectsList, false));

        // Добавляется новый элемент в итоговый лист. В нём лист с тремя элементами и пустой лист.
        resultList.add(new AbstractObject(6, 32, "united1", Arrays.asList("one", "two", "three"), new ArrayList<>(), true));

        // Добавляется новый элемент в итоговый лист. В нём лист с двумя элементами и пустой лист.
        resultList.add(new AbstractObject(18, 32, "dlsfkjsdfks", Arrays.asList("1", "2"), new ArrayList<>(), false));

        // Изменяется содержимое листа объектов. Теперь там 6 объектов. В первом объекте лист с одним элементом и пустой лист.
        // Во втором объекте лист с двумя элементами и пустой лист. В третьем объекте лист с двумя элементами и лист,
        // содержащий объект с листом из двух элементов и пустым листом. В четвёртом объекте лист с двумя элементами и пустой лист.
        // В пятом объекте лист с тремя элементами и пустой лист. В шестом объекте лист с одним элементом и пустой лист.
        // В итоговый лист добавляется объект, который содержит лист с двумя элементами и пустой лист.
        // Возвращается итоговый лист.
        objectsList = Arrays.asList(new AbstractObject(1, 1, "ok", Arrays.asList("one"), new ArrayList<>(), true),
                new AbstractObject(2, 21, "oka", Arrays.asList("one, two"), new ArrayList<>(), false),
                new AbstractObject(8, 22, "okay", Arrays.asList("one, mistake"), Arrays.asList(new AbstractObject(1, 2, "mistake", Arrays.asList("one, two"), new ArrayList<>(), true)), false),
                new AbstractObject(7, 23, "okay2", Arrays.asList("one", "mistake"), new ArrayList<>(), false),
                new AbstractObject(6, 24, "okay3", Arrays.asList("one", "two", "three"), new ArrayList<>(), false),
                new AbstractObject(5, 25, "mistake", Arrays.asList("one"), new ArrayList<>(), false));
        // Добавил добавление :)
        resultList.add(new AbstractObject(8, 72, "lol", Arrays.asList("1", "2", "3"), objectsList, true));
        resultList.add(new AbstractObject(7, 32, "113", Arrays.asList("13", "12"), new ArrayList<>(), true));
        return resultList;
    }

}
