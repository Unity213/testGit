package s;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Test {


    private static  boolean flga = true;

    private static volatile Te s = new Te();

    public static void main(String[] args) {



        Thread asd = new Thread(() -> {

//            System.out.println(s.f);
            int c = 0;
//            LockSupport.park();
            while (s.f) {
                c++;
            }
            System.out.println(c);
            System.out.println("end");


        });
        asd.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        LockSupport.unpark(asd);
        new Thread(() -> {
            s.f = false;
            flga = false;
        }).start();

    }

    static class Te {
        boolean f = true;
    }


}
