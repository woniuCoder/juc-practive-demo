package reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class CinemaBookSeat {

    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat() {
        lock.lock();
        try {
            System.out.println("开始预定座位");
            Thread.sleep(1000);
            System.out.println("完成预定位置");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
           bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
    }

}
