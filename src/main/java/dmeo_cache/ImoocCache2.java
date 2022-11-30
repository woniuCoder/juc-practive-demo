package dmeo_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 装饰者模式添加缓存功能
 */
public class ImoocCache2<A, V> implements Computable<A,V> {

    private final Map<A, V> cache = new HashMap<>();

    private final Computable<A, V> computable;

    public ImoocCache2(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache2<String, Integer> stringIntegerImoocCache2 = new ImoocCache2<>(new ExpensiveFunction());
//        Integer compute1= stringIntegerImoocCache2.compute("12");
//        System.out.println("1:" + compute1);
//        Integer compute2 = stringIntegerImoocCache2.compute("12");
//        System.out.println("2" + compute2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer compute = stringIntegerImoocCache2.computable.compute("666");
                    System.out.println(compute);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer compute = stringIntegerImoocCache2.computable.compute("667");
                    System.out.println(compute);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer compute = stringIntegerImoocCache2.computable.compute("668");
                    System.out.println(compute);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
