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

                //new ThreadPoolExecutor.AbortPolicy()  //抛异常
                //new ThreadPoolExecutor.DiscardPolicy()   //不抛异常 直接放弃
                //new ThreadPoolExecutor.DiscardOldestPolicy()  //尝试与队列最前⾯的任务去竞争，不抛出异常
                new ThreadPoolExecutor.CallerRunsPolicy()         //谁调用谁处理  比如main进程

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
