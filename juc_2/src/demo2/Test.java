package demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "B").start();
    }


}

class Ticket {
    private Integer saleNum = 0;
    private Integer lastNum = 20;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        if (lastNum > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            saleNum++;
            lastNum--;
            System.out.println(Thread.currentThread().getName() + "卖出了第" + saleNum + "张票" +
                    "剩余" + lastNum + "张票");
        }
        lock.unlock();
    }
}
