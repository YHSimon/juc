package demo;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        for (int i=0;i<40;i++){
            new Thread(()->{
                ticket.sale();
            },"A").start();
        }
        for (int i=0;i<40;i++){
            new Thread(()->{
                ticket.sale();
            },"B").start();
        }
    }

}
class Ticket{
    private Integer saleNum=0;
    private Integer lastNum=20;

    public synchronized void sale(){
        if(lastNum>0){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            saleNum++;
            lastNum--;
            System.out.println(Thread.currentThread().getName()+"卖出了第"+saleNum+"张票"+
                    "剩余"+lastNum+"张票");
        }
    }
}