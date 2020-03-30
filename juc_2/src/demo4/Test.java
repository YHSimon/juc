package demo4;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 还具备限时性的特点，可以判断某个线程在⼀定时间内能否获取到锁，tryLock 返回⼀
 * 个 boolean 的值，true 表示可以拿到锁，false 表示拿不到锁。
 */
public class Test {
    public static void main(String[] args) {
        TimeLock timeLock=new TimeLock();
        new Thread(()->{
            timeLock.getLock();
        },"A").start();

        new Thread(()->{
            timeLock.getLock();
        },"B").start();
    }
}
class  TimeLock{
    private ReentrantLock lock=new ReentrantLock();

    public void getLock(){
        try {
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"拿到了锁");
                TimeUnit.SECONDS.sleep(5);
            }else{
                System.out.println(Thread.currentThread().getName()+"没有拿到锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //判断当前线程是否持有锁
            boolean heldByCurrentThread = lock.isHeldByCurrentThread();
            if(heldByCurrentThread){
                System.out.println(Thread.currentThread().getName()+"持有锁");
                lock.unlock();
            }
        }
    }
}