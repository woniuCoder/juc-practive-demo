package cyclicbarrier;

public class CyclicBarrier {

    public static void main(String[] args) {
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人到场了，出发");
            }
        });
        for (int i = 0; i < 5 ; i++) {
            new Thread(new Task(i, cyclicBarrier)).start();
        }
    }

    static class Task implements Runnable {

        private int id;
        private java.util.concurrent.CyclicBarrier cyclicBarrier;

        public Task(int id, java.util.concurrent.CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("所有人" + id + "现在前往集合地点");
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("所有人" + id + "到了集合地点，开始等待其他人到达");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
