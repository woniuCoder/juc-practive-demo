package condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo01 {

    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    private void method01() {
        reentrantLock.lock();
        try {
            System.out.println("条件不满足开始等待await");
            condition.await();
            System.out.println("条件满足开始执行后续任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void method02() {
        reentrantLock.lock();
        try {
            System.out.println("准备工作完成，唤醒其他线程");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo01 demo01 = new ConditionDemo01();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    demo01.method02();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        demo01.method01();
    }

}
