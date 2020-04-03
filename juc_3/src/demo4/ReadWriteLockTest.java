package demo4;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁  要求同时可以读（不强制也一样） 但同一时刻只能有一个进程进行写入
//读取锁（共享锁）  写入锁（独占锁）
public class ReadWriteLockTest {

    public static void main(String[] args) {
        Cache cache=new Cache();
        for (int i = 0; i < 10; i++) {
            int temp=i;
            new Thread(()->{
                cache.write(temp);
            },String.valueOf(i)).start();
        }


        for (int i=0 ;i<10;i++){
            int temp=i;
            new Thread(()->{
                cache.read(temp);
            },String.valueOf(i)).start();
        }

    }

}

class Cache{
    private Map<Integer,Integer> map=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    public void read(Integer i){
        //添加读取锁  实际上允许多线程同时读取数据，所以可以不加读取锁
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"读取开始...");
        map.get(i);
        System.out.println(Thread.currentThread().getName()+"读取完毕...");
        //释放读取锁
        readWriteLock.readLock().unlock();
    }


    public void write(Integer i){
        //添加写入锁
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"写入开始...");
        map.put(i, i);
        System.out.println(Thread.currentThread().getName()+"写入完毕...");
        //释放写入锁
        readWriteLock.writeLock().unlock();
    }
}
