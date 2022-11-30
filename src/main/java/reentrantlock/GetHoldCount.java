package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class GetHoldCount {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.lock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.lock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.lock();

        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();
        System.out.println(reentrantLock.getHoldCount());
        reentrantLock.unlock();

        System.out.println(reentrantLock.getHoldCount());
    }

}
