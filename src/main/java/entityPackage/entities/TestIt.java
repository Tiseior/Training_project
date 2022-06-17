package entityPackage.entities;

public class TestIt {
    private Long long1;
    public static Long long4 = 1L;
    protected Long long2;
    public Long long3;

    public TestIt(){
        long1 = 1L;
        long2 = 2L;
        long3 = 3L;
    }

    public void Test(){
        System.out.println(long4);
    }
}
