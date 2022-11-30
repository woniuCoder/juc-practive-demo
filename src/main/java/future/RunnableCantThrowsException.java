package future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在 run方法中无法抛出 checked exception
 */
public class RunnableCantThrowsException {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public void callable() {
        ExecutorService executorService = Executors.newFixedThreadPool(19);

    }

}
