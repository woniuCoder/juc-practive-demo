package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i< 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFieldUpdaterDemo atomicIntegerFieldUpdaterDemo = new AtomicIntegerFieldUpdaterDemo();
        Thread thread = new Thread(atomicIntegerFieldUpdaterDemo);
        Thread thread1 = new Thread(atomicIntegerFieldUpdaterDemo);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("tom" + tom.score);
        System.out.println("peter" + peter.score);
    }

    public static class Candidate {
        volatile int score;
    }

}
