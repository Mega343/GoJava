package EEModule3;


public class Worker implements Runnable {

    private SemaphoreImpl semaphore;
    private int permits;

    public Worker(SemaphoreImpl semaphore, int permits) {
        this.semaphore = semaphore;
        this.permits = permits;
    }

    @Override
    public void run() {
        try {
            semaphore.getAvailablePermits();
            Thread.sleep(100);
            semaphore.acquire();
            Thread.sleep(100);
            semaphore.getAvailablePermits();
            semaphore.release();
            Thread.sleep(100);
            semaphore.acquire(permits);
            Thread.sleep(100);
            semaphore.release(permits);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
