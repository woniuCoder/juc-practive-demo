package reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static class Outputer {
        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.println(name.charAt(i));
                }
                System.out.println("");
            } finally {
                lock.unlock();
            }
        }

    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(() -> {
           while (true) {
               try {
                   Thread.sleep(5);
               } catch (Exception exception) {
                   exception.printStackTrace();
               }
               outputer.output("网站");
           }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                outputer.output("悟空");
            }
        }).start();
    }

    public static void main(String[] args) {
        new LockDemo().init();
    }
}
