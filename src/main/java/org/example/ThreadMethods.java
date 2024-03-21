package org.example;


class MyThread extends Thread
{
    public MyThread(String name)
    {
        //assigning a name to our thread by calling super class thread
        super(name);

    }

    public void run()
    {
        int count=1;
        while(true)
        {
            System.out.println(count++);
            try                // try block is used as sleep method can be Interrupted so we handle InterruptedException

            {
                //to make the thread delay for some time, done in milliseconds
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
        //to start the thread
        t.start();
        //after the first print when the thread was sleeping for 1000 milliseconds it was interrupted/woken up by interrupt() to continue its execution
        t.interrupt();

    }

}