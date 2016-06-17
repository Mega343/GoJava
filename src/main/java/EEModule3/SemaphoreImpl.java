package EEModule3;


public class SemaphoreImpl implements Semaphore {

    private final int permitsLimit;
    private int permits;
    private final Object lock = new Object();

    public SemaphoreImpl(int permits) {
        this.permits = permits;
        this.permitsLimit = permits;
    }

    @Override
    public void acquire() throws InterruptedException {
        synchronized (lock) {
            if (permits > 0) {
                permits--;
                System.out.println("Acquired permits");
            }
            else{
                lock.wait();
            }
        }
    }

    @Override
    public void acquire(int permits) throws InterruptedException {
        if(permits > 0) {
            synchronized (lock) {
                if (this.permits >= permits) {
                    this.permits -= permits;
                    System.out.println(permits + " permits acquired.");
                } else {
                    lock.wait();
                }
            }
        }
        else System.out.println("Error. Permits should be positive number");
    }

    @Override
    public void release() {
        synchronized (lock){
            if(permits < permitsLimit){
                permits++;
                System.out.println("Permits released");
            }
            lock.notifyAll();
        }
    }

    @Override
    public void release(int permits) {
        if(permits > 0) {
        synchronized (lock){
            if(permits <= permitsLimit){
                this.permits +=permits;
                System.out.println(permits + " permits released.");
            }
                lock.notifyAll();
        }
        }
        else System.out.println("Error. Permits should be positive number");
    }

    @Override
    public int getAvailablePermits() {
        System.out.println("Available permits: " + permits);
        return permits;
    }
}
