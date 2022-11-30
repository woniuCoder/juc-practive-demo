package dmeo_cache;

public class Test implements AutoCloseable {

    private Integer code;

    public Test(Integer code) {
        this.code = code;
        try {
            this.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        System.out.println(code);
    }

    @Override
    public void close() throws Exception {
        System.out.println(code - 1);
    }
}
