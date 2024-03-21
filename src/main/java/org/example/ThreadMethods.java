package org.example;


class MyThread extends Thread
{
    public MyThread(String name)
    {
        super(name);

    }

    public void run()
    {
        int count=1;
        while(true)
        {
            System.out.println(count++);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
    }
}

public class ThreadMethods {

    public static void main(String[] args)
    {

        MyThread t=new MyThread("My Thread 1");
        t.start();
        t.interrupt();

    }

}