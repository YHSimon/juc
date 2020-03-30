package demo8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
//        List<String> list =new ArrayList<>(); 多次执行会报ConcurrentModificationException异常
        /**
         * 方式一  使用Vector
         * List<String> list=new Vector<>(); //方式一 使用Vector
         */

        /**
         * 方式二 使用 Collections.synchronizedList()
         * List<String> list= Collections.synchronizedList(new ArrayList<>());
         */

        /**
         * 方式三 使用 JUC的CopyOnWriteArrayList
         */
        List<String> list=new CopyOnWriteArrayList<>();
        for (int i=0;i<10;i++){
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("a");
                System.out.println(list);
            }).start();
        }
        /**
         * set
         */
        Set<String> set=new CopyOnWriteArraySet<>();
        for (int i=0;i<10;i++){
            int temp = i;
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                set.add(String.valueOf(temp));
                System.out.println(set);
            }).start();
        }

        /**
         * Map
         */
        Map<String,String> map=new ConcurrentHashMap<>();
        for (int i=0;i<10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(UUID.randomUUID().toString().substring(0,3), String.valueOf(i));
            System.out.println(map);
        }
    }
}
