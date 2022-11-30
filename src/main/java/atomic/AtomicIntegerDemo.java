package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类
 */
public class AtomicIntegerDemo implements Runnable {

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public void incrementAtomic() {
        atomicInteger.getAndAdd(-10);
    }

    private static volatile int basicCount = 0;

    public void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws Exception {
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        Thread thread1 = new Thread(atomicIntegerDemo);
        Thread thread2 = new Thread(atomicIntegerDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子类结果：" + atomicInteger.get());
        System.out.println(basicCount);
    }
}
