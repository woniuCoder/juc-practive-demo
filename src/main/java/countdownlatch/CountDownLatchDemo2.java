package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(no + " : 准备完毕");
                    try {
                        //等待countDown的场景信息
                        latch.await();
                        System.out.println(no + " : 开始跑步了");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响了");
        latch.countDown();
    }

}