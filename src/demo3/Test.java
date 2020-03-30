package demo3;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                a.test(i);
            }
        }).start();

        //唤醒线程
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.test2();
        }).start();
    }
}

//资源
class A {
    public synchronized void test(int i) {
        if (i == 5) {
            try {
                this.wait();
//                3s后唤醒 方式一
//                this.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + "----------------");
    }

    /**
     * notify唤醒  方式二
     */
    public synchronized void test2(){
        this.notify();
    }
}