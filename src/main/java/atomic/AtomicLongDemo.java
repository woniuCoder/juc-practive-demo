package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(atomicLong));
        }
    }

    private static class Task implements Runnable {

        private AtomicLong atomicLong;

        public Task(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicLong.incrementAndGet();
            }
        }
    }
}
