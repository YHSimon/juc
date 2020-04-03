package demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 根据线程池最底层的构造器 推荐自定义线程池
 */
public class Test {
    public static void main(String[] args) {
        //单例线程池，缓冲线程池中只有一个线程读写
        // ExecutorService executorService= Executors.newSingleThreadExecutor();
        //指定线程个数的线程池
        //ExecutorService executorService=Executors.newFixedThreadPool(5);
        //缓冲线程池，线程个数由电脑硬件配置决定
        ExecutorService executorService=Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int temp=i;
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+":"+temp);
            });
        }
        //关闭
        executorService.shutdown();
    }
}
