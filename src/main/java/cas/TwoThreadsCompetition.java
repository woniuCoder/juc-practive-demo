package cas;

public class TwoThreadsCompetition implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }


    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition twoThreadsCompetition = new TwoThreadsCompetition();
        twoThreadsCompetition.value = 0;
        Thread thread = new Thread(twoThreadsCompetition);
        Thread thread1 = new Thread(twoThreadsCompetition);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(twoThreadsCompetition.value);
    }

    @Override
    public void run() {
        compareAndSwap(0, 1);
    }

}
