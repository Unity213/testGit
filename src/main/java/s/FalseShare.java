package s;

import sun.misc.Contended;

public
class

FalseShare {


    public

    static void
    main
            (
                    String
                            []
                            args
            )

            throws

            InterruptedException {

        testPointer
                (
                        new

                                Pointer
                                ());


    }


    private

    static void
    testPointer
            (
                    Pointer
                            pointer
            )

            throws

            InterruptedException {


        long
                start
                =

                System
                        .
                                currentTimeMillis
                                        ();


        Thread
                t1
                =

                new

                        Thread
                        (()

                                ->

                        {


                            for

                            (
                                    int
                                    i
                                    =

                                    0
                                    ;
                                    i
                                            <

                                            100000000
                                    ;
                                    i
                                            ++) {

                                pointer
                                        .
                                        x.value
                                        ++;


                            }


                        });


        Thread
                t2
                =

                new

                        Thread
                        (()

                                ->

                        {


                            for

                            (
                                    int
                                    i
                                    =

                                    0
                                    ;
                                    i
                                            <

                                            100000000
                                    ;
                                    i
                                            ++) {

                                pointer
                                        .
                                        y.value
                                        ++;


                            }


                        });


        t1
                .
                        start
                                ();

        t2
                .
                        start
                                ();

        t1
                .
                        join
                                ();

        t2
                .
                        join
                                ();


        System
                .
                        out
                .
                        println
                                (
                                        System
                                                .
                                                        currentTimeMillis
                                                                ()

                                                -
                                                start
                                );


        System
                .
                        out
                .
                        println
                                (
                                        pointer
                                );


    }

}


class


Pointer {


    volatile

    MyLong
            x = new MyLong();


    volatile

    MyLong
            y= new MyLong();

    @Override
    public String toString() {
        return "Pointer{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    @Contended
    class

    MyLong

    {


        volatile

        long
                value
                ;

    }
}
