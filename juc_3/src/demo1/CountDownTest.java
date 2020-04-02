package demo1;


import java.util.concurrent.CountDownLatch;

/**
 * 减法计数器
 */
public class CountDownTest {
    public static void main(String[] args) {
        //优先执行，执行完毕之后，才能执行main  (克制并发)
        //1. 实例化计数器 100
        CountDownLatch countDownLatch=new CountDownLatch(100);


        new Thread(()->{
            for (int i=0;i<100;i++){
                System.out.println("++++++Thread");
                //countDownLatch.countDown();
            }
        }).start();

        //主线程
        for (int i=0;i<100;i++){
            countDownLatch.countDown();
            System.out.println("main-------");
        }

        //2.调用 await 方法  计算器不等于0,则后续线程一直等待;反之，计数器清零，后面的线程可以运行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
