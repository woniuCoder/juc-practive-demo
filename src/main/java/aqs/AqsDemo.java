package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsDemo {

    private final Sync sync = new Sync();

    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            if (getState() == 1) {
                return 1;
            }
            return -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AqsDemo aqsDemo = new AqsDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                    aqsDemo.await();
                    System.out.println(Thread.currentThread().getName() +  "开始运行");
                }
            }).start();
        }
        Thread.sleep(5000);
        aqsDemo.signal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "尝试获取latch，获取失败那就等待");
                aqsDemo.await();
                System.out.println(Thread.currentThread().getName() +  "开始运行");
            }
        }).start();
    }

}
