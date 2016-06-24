package EEModule3point2;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareSumImpl implements SquareSum {

    @Override
    public long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException {
        long result = 0;
        int startPosition = 0;
        int endPosition;
        int elementsForThread = (values.length < numberOfThreads) ? 1 : values.length / numberOfThreads;
        List<Callable<Long>> listResults = new ArrayList<>();

        if(numberOfThreads <= 0){
            throw new IllegalArgumentException("Number of threads should be positive");
        }

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        while(startPosition < values.length) {
            endPosition = (startPosition + elementsForThread > values.length)
                    ? values.length : startPosition + elementsForThread;
            final int finalStartPosition = startPosition;
            final int finalEndPosition = endPosition;
            listResults.add(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long result = 0;
                    System.out.println("Thread start work " + Thread.currentThread().getName() +
                            " with start position: " + finalStartPosition + " and end position " + finalEndPosition);
                    for (int j = finalStartPosition; j < finalEndPosition; j++) {
                        result += (long) (values[j]*values[j]);
                    }
                    System.out.println(Thread.currentThread().getName() + " " + result);
                    return result;
                }
            });
            startPosition = endPosition;
        }

        List<Future<Long>> results = executor.invokeAll(listResults);
        for(Future<Long> value : results){
            result = result + value.get();
        }
        executor.shutdown();
        return result;
    }
}
