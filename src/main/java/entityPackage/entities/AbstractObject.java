package entityPackage.entities;

import java.util.List;

public class AbstractObject {
    public int id;
    public Integer count;
    public String name;
    public List<String> stringList;
    public List<AbstractObject> abstractObjectList;
    public Boolean isAlright;


    public AbstractObject(int id, Integer count, String name, List<String> stringList, List<AbstractObject> abstractObjectList, Boolean isAlright) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.stringList = stringList;
        this.abstractObjectList = abstractObjectList;
        this.isAlright = isAlright;
    }

    public int getId() {
        return id;
    }
}
