package EEModule3point2;


import java.util.concurrent.ExecutionException;

public interface SquareSum  {
    long getSquareSum(int[] values, int numberOfThreads) throws ExecutionException, InterruptedException;
}
