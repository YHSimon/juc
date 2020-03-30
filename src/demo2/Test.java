package demo2;

public class Test {
    public static void main(String[] args) {
        Account account=new Account();
        new Thread(account,"A").start();
        new Thread(account,"B").start();
    }
}

/**
 * 统计程序的访问量
 * 资源与Runnable绑定 耦合度高
 */

class  Account implements Runnable{

    private static  int num;
    @Override
    public synchronized void run() {
        num++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"是第"+num+"位访客");
    }
}
