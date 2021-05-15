package s;

import com.alibaba.dubbo.cache.support.jcache.JCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 衡 on 2019/4/8.
 */
public class SS {

    private static AtomicInteger s = new AtomicInteger();
    private static int c;

    public static void main(String[] args)  {

//        Object obj = new Object();
//        Thread thread = new Thread(() -> {
//
//            LockSupport.park();
//            System.out.println("=="+obj.toString());
//        });
//
//        thread.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Object blocker = LockSupport.getBlocker(thread);
//        System.out.println(blocker);
//
//        LockSupport.unpark(thread);


        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        Thread main = Thread.currentThread();
//        executor.execute(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("gou");
//            main.interrupt();
//        });
        BlockingQueue<String> s = new ArrayBlockingQueue<String>(5);

        s.offer("1");
        Thread.currentThread().interrupt();
        String take = null;
        try {
            take = s.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("take 第一次结束 :"+take);


        String take2 = null;
        try {
            take2 = s.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("take 第2次结束 :"+take2);



    }




}
