package demo4;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        /**情景：
         * 1. 只有一个对象时，线程同步
         * 2. 多个对象时，不需要排队
         * 3. 类中既有synchronized修饰的方法 也有未修饰的
         *         未修饰的不受影响 直接输出
         *
         */
        Data data = new Data();
        Data data2 = new Data();
        new Thread(() -> {
            data.fun1();
        }).start();


        new Thread(() -> {
            data.fun2();
            //data2.fun2();
        }).start();

        new Thread(() -> {
            data.fun3();  //未修饰的方法
        }).start();


    }

}

//synchronized修饰的是非静态方法
class Data {
    public synchronized void fun1() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1....");
    }

    public synchronized void fun2() {
        System.out.println("2....");
    }

    public void fun3() {
        System.out.println("3....");
    }

}