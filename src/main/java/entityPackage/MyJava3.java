package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entitiesCreate.CreateAbstractObject;

import java.util.List;

public class MyJava3 {

    // Задачки из Jira, этап 2

    public static void main(String[] args) {

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        List<AbstractObject> filteredList = list.stream().filter(e -> e.id != 1).toList();
    }
}
