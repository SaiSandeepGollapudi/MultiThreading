package org.example;



class MyYieldThread extends Thread
{
    public void run(){
        int count=1;

        while(true){
            System.out.println(count++ +"My MyYieldThread");
        }
    }
}
public class Yield {
    public static void main(String[] args)
    {
        MyYieldThread myYieldThread   =new MyYieldThread();
        myYieldThread.start();

        int count=1;

        while(true){
            System.out.println(count++ +"Main MyYieldThread");
            Thread.yield();// A hint to the scheduler that the current thread is willing to yield its current use of a processor
        }

    }
}
