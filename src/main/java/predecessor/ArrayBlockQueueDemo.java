package predecessor;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * ArrayBlockQueue
 */
public class ArrayBlockQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        Interviewer interviewer = new Interviewer(queue);
        Consumer consumer = new Consumer(queue);
        Thread thread1 = new Thread(interviewer);
        thread1.start();
        Thread thread2 = new Thread(consumer);
        thread2.start();
    }

}

class Interviewer implements Runnable {
    ArrayBlockingQueue<String> queue;

    public Interviewer(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("十个候选人都来了");
        for (int i = 0; i < 10; i++) {
            String candidate = "candidate" + i;
            try {
                queue.put(candidate);
                System.out.println("安排好了：" + candidate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            queue.put("stop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    ArrayBlockingQueue<String> queue;

    public Consumer(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String message;
        try {
            while (!(message = queue.take()).equals("stop")) {
                System.out.println(message + "哪一位了");
            }
            System.out.println("所有候选人都结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}