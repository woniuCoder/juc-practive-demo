package dmeo_cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ImoocCache {

    private final HashMap<String, Integer> cache = new HashMap<>();

    private Integer computer(String userId) throws InterruptedException {
        Integer integer = cache.get(userId);
        if (integer == null) {
            integer = doCompute(userId);
        }
        return integer;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        ImoocCache imoocCache = new ImoocCache();
        Integer computer = imoocCache.computer("13");
        System.out.println("结果1" + computer);
        Integer computer1 = imoocCache.computer("13");
        System.out.println("结果2" + computer1);
    }
}
