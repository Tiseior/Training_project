package entityPackage.entitiesCreate;

import entityPackage.entities.AbstractObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateAbstractObject {

    public List<AbstractObject> createListOfAbstractObjects(){
        List<AbstractObject> resultList = new ArrayList<>();
        resultList.add(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true));

        List<AbstractObject> objectsList = Arrays.asList(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true));
        resultList.add(new AbstractObject(2, 32, "anyway", Arrays.asList("one"), objectsList, true));

        objectsList = Arrays.asList(new AbstractObject(13, -2, "name", new ArrayList<>(), new ArrayList<>(), true),
                new AbstractObject(11, 2223, "not", Arrays.asList("one", "two", "three", "four"), new ArrayList<>(), false),
                new AbstractObject(4, 23, "inside", Arrays.asList("one", "two"), Arrays.asList(new AbstractObject(1, 2, "name", new ArrayList<>(), new ArrayList<>(), true)), false));
        resultList.add(new AbstractObject(1, 32, "input", Arrays.asList("one", "two", "three"), objectsList, false));

        resultList.add(new AbstractObject(3, 32, "23anywhere", Arrays.asList("one", "two", "three", "four"), new ArrayList<>(), true));

        objectsList = Arrays.asList(new AbstractObject(1, 1, "name", Arrays.asList("one"), new ArrayList<>(), true),
                new AbstractObject(2, 2, "23", Arrays.asList("one"), new ArrayList<>(), false));
        resultList.add(new AbstractObject(15, 32, "sometimes", Arrays.asList("one", "two", "three", "four"), objectsList, false));

        resultList.add(new AbstractObject(6, 32, "united1", Arrays.asList("one", "two", "three"), new ArrayList<>(), true));

        resultList.add(new AbstractObject(18, 32, "dlsfkjsdfks", Arrays.asList("1", "2"), new ArrayList<>(), false));

        objectsList = Arrays.asList(new AbstractObject(1, 1, "ok", Arrays.asList("one"), new ArrayList<>(), true),
                new AbstractObject(2, 21, "oka", Arrays.asList("one, two"), new ArrayList<>(), false),
                new AbstractObject(8, 22, "okay", Arrays.asList("one, mistake"), Arrays.asList(new AbstractObject(1, 2, "mistake", Arrays.asList("one, two"), new ArrayList<>(), true)), false),
                new AbstractObject(7, 23, "okay2", Arrays.asList("one", "mistake"), new ArrayList<>(), false),
                new AbstractObject(6, 24, "okay3", Arrays.asList("one", "two", "three"), new ArrayList<>(), false),
                new AbstractObject(5, 25, "mistake", Arrays.asList("one"), new ArrayList<>(), false));
        resultList.add(new AbstractObject(7, 32, "113", Arrays.asList("13", "12"), new ArrayList<>(), true));
        return resultList;
    }

}
