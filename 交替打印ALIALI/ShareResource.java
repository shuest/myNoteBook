import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShareResource {
    private int number = 1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    public void printA() {
        lock.lock();
        while(number!= 1) {
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        number = 2;
        c2.signal();
        lock.unlock();
    }
    public void printL() {
        lock.lock();
        while(number!= 2) {
            try {
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        number = 3;
        c3.signal();
        lock.unlock();
    }
    public void printI() {
        lock.lock();
        while(number!= 3) {
            try {
                c3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName());
        number = 1;
        c1.signal();
        lock.unlock();
    }
}
