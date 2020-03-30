package demo5;

import java.util.concurrent.TimeUnit;

/**
 * 如果 synchronized 修饰的是静态⽅法，则锁定的是类，⽆论多少个对象调⽤，都会同步
 * 如果 synchronized 静态⽅法和实例⽅法同时存在，静态⽅法锁类，实例⽅法锁对象
 */
public class Test {
    public static void main(String[] args) {

        Data2 data = new Data2();
        Data2 data2 =new Data2();
        new Thread(()->{
            data.fun1();
        }).start();

        new Thread(()->{
            data2.fun1();
        }).start();

//        new Thread(()->{
//            data2.fun1();
//        }).start();

//        new Thread(()->{
//            data.fun2();
//        }).start();

//        new Thread(()->{
//            data2.fun2();
//        }).start();

    }
}

class Data2 {
    public synchronized static void fun1() {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1.....");
    }

    public synchronized static void fun3(){
        System.out.println("3......");
    }

    public synchronized void fun2() {
        System.out.println("2.....");
    }

}