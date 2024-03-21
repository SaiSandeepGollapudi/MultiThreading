package org.example;


class MyDaemonJoinThread extends Thread
{
    public void run(){
        int count=1;

        while(true){
            System.out.println(count++);
        }
    }
}
public class DaemonJoin {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonJoinThread myDaemonJoinThread= new MyDaemonJoinThread();
        //Daemon threads are dependent threads so if  main method is terminating then daemon also terminates making the thread above nto to print anything
        myDaemonJoinThread.setDaemon(true);
        myDaemonJoinThread.start();
        //Thread is the predefined parent class
        Thread mainThread = Thread.currentThread();
        //Now even though daemon terminates, Instead of terminate Join will keep it waiting till the other thread is done
        mainThread.join();



    }
}
