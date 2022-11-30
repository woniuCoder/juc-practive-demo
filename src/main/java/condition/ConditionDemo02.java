package condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用condition演示生产者消费者模式
 */
public class ConditionDemo02 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo02 conditionDemo02 = new ConditionDemo02();
        Producer producer = conditionDemo02.new Producer();
        Consumer consumer = conditionDemo02.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        public void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列是空的，等待数据");
                        try {
                            notEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("从队列中取走数据" + queue.size() + "元素");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        public void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        System.out.println("队列是满的，等待空余");
                        try {
                            notFull.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("从队列中添加数据" + queue.size() + "元素");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
