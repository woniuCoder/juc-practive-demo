package dmeo_cache;

/**
 * 耗时计算的实现类，实现了Computable接口
 */
public class ExpensiveFunction implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {
        Thread.sleep(5000);
        return Integer.valueOf(arg);
    }

}
