package demo7;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProCon2 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                data.increase();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                data.decrease();
            }
        }, "B").start();
    }
}

class Data {
    private Integer num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 加一
     */
    public void increase() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "-->" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    /**
     * 减一
     */
    public  void decrease() {
        lock.lock();
        try{
            while (num == 0) {
                condition.await();
            }
            num--;
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "-->" + num);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}