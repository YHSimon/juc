package demo3;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 除了可重⼊之外，还有⼀个可中断的特点：
 * 允许在某个线程等待时，主动去中断线程，不需要获取锁，但是会抛出异常
 */
public class Test {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        lock.lock();
        Thread thread=new Thread(()->{
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"interrupted");
        });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }
}