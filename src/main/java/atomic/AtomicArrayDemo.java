package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicArrayDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Thread[] threadsAdd = new Thread[100];
        Thread[] threadsDec = new Thread[100];
        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);
        for (int i = 0; i < 100; i++) {
            threadsAdd[i] = new Thread(incrementer);
            threadsDec[i] = new Thread(decrementer);
            threadsAdd[i].start();
            threadsDec[i].start();
        }
        for (int i = 0; i < 100; i++) {
            threadsAdd[i].join();
            threadsDec[i].join();
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println("获取值" + atomicIntegerArray.get(i));
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现了错误：" + i);
            }
        }
        System.out.println("运行结束");
    }

}

class Decrementer implements Runnable {

    private AtomicIntegerArray atomicIntegerArray;

    public Decrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndDecrement(i);
        }
    }
}


class Incrementer implements Runnable {

    private AtomicIntegerArray atomicIntegerArray;

    public Incrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndIncrement(i);
        }
    }
}