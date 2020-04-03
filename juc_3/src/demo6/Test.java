package demo6;

import java.util.concurrent.*;


//4种拒绝策略
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService=null;
        executorService=new ThreadPoolExecutor(
                2,
                3,
                1L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()
                //new ThreadPoolExecutor.DiscardPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        //开始营业
        for (int i = 0; i < 6; i++) {
            executorService.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-->办理业务'");
            });
        }
        executorService.shutdown();
    }
}
