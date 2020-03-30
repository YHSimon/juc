package demo6;

//生产者与消费者
public class ProCon {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                data.increase();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                data.decrease();
            }
        }, "B").start();
    }
}

class Data {
    private Integer num = 0;

    /**
     * 加一
     */
    public synchronized void increase() {
        while(num!=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        this.notify();
        System.out.println(Thread.currentThread().getName() + "-->" + num);
    }

    /**
     * 减一
     */
    public synchronized void decrease() {
        while(num==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        this.notify();
        System.out.println(Thread.currentThread().getName() + "-->" + num);
    }
}