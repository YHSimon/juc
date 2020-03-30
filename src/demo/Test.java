package demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口 实现多线程
 */
public class Test {
    public static void main(String[] args) {
        //Runnable

//        Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runnable");
//            }
//        };

        //lambda
        Runnable runnable= () -> System.out.println("runnable");
        Thread thread = new Thread(runnable);
        thread.start();

        MyCallable myCallable=new MyCallable();
        FutureTask futureTask=new FutureTask(myCallable);
        Thread thread1=new Thread(futureTask);
        thread1.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}

class MyCallable implements Callable {

    @Override
    public String call() throws Exception {
        return "callable";
    }
}