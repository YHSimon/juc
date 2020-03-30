package demo6;

import java.util.concurrent.TimeUnit;

/**
 * 锁定的传入对象是同一个对象，实现线程同步
 * 锁定的传入对象不是同一个对象，线程不同步
 */
public class Test {
    public static void main(String[] args) {
        Data3 data3=new Data3();
        for(int i=0;i<5;i++){
            Integer num=Integer.valueOf(2);
            //Integer num=Integer.valueOf(128);
            //Integer num=Integer.valueOf(i);
            new Thread(()->{
                data3.fun(num);
            }).start();
        }
    }
}

/**
 *synchronized修饰代码块\
 *
 * 同步时结果为
 *  1.  输出start
 *      2s
 *  2.  输出end
 */
class Data3{
    public void fun(Integer num){
        synchronized (num){
            System.out.println(num+"start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end...");
        }
    }
}