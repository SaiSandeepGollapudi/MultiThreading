package org.example;
 class MyThread5 extends Thread {
    public void run() {
        int i = 1;
        while (true) {
            System.out.println(i + "Hello");
            i++;
            if(i==10000)
                break;

        }
    }
}

public class ThreadTest{
    public static void main(String[] args) {

        MyThread5 t = new MyThread5();
        t.start();


        int i = 1;
        while (true) {
            System.out.println(i + "World");
            i++;
           if(i==10000)
            break;

        }


    }

}