package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class CountDownLatchDemo1And2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(no + " : 准备完毕");
                    try {
                        //等待countDown的场景信息
                        begin.await();
                        System.out.println(no + " : 开始跑步了");
                        System.out.println("----------------");
                        //生成随机跑步时间
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(no + "：跑到终点了");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响了");
        begin.countDown();
        end.await();
        System.out.println("比赛结束了");
    }
}
