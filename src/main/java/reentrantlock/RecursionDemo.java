package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static void accessResource() {
        reentrantLock.lock();
        System.out.println("对资源进行处理");
        try {
            if (reentrantLock.getHoldCount() < 5) {
                System.out.println(reentrantLock.getHoldCount());
                accessResource();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        accessResource();
    }

}
