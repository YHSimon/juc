package demo5;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        DeadLock deadLock1=new DeadLock(1);
        DeadLock deadLock2=new DeadLock(2);

        new Thread(()->{
            deadLock1.lock();
        },"A").start();

        /**  不加停歇时间 或停歇时间过短 易形成死锁
         *  try {
         *             TimeUnit.MILLISECONDS.sleep(500);
         *         } catch (InterruptedException e) {
         *             e.printStackTrace();
         *         }
         */

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            deadLock2.lock();
        },"B").start();
    }
}
class DeadLock{
    private int num;
    private static Data data1=new Data();
    private static Data data2=new Data();

    public DeadLock(int num){
        this.num=num;
    }

    public void lock(){
        if(num==1){
            System.out.println(Thread.currentThread().getName()+"获得data1，等待获取data2");
            synchronized (data1){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (data2){
                    System.out.println(Thread.currentThread().getName()+"用餐完毕");
                }
            }
        }

        if(num==2){
            System.out.println(Thread.currentThread().getName()+"获得data2，等待获取data1");
            synchronized (data2){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (data1){
                    System.out.println(Thread.currentThread().getName()+"用餐完毕");
                }
            }
        }
    }
}
class Data{

}