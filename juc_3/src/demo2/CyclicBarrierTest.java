package demo2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        //定义一个加法计数器，当计数器的值累加到30时，输出"放行"
        CyclicBarrier cyclicBarrier=new CyclicBarrier(30,()->{
            System.out.println("放行");
        });

        for (int i=1;i<=40;i++){
            int temp=i;
            new Thread(()->{
                System.out.println("-->"+temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
