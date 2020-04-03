package demo7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Test {
    public static void main(String[] args) {
        /***
         Long sum=0L;
         Long startTime=System.currentTimeMillis();
         for(Long i=0L;i<=20_0000_0000L;i++){
         sum+=i;
         }
         Long endTime=System.currentTimeMillis();
         System.out.println(sum+"，耗时"+(endTime-startTime));

         * 结果  2000000001000000000，耗时16631
         * */

        Long startTime=System.currentTimeMillis();
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Long> task=new ForkJoinDemo(0L, 20_0000_0000L);
        forkJoinPool.execute(task);
        Long sum=0L;
        try {
            sum=task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Long endTime=System.currentTimeMillis();
        System.out.println(sum+",耗时"+(endTime-startTime));
    }
}
