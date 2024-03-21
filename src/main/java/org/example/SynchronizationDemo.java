package org.example;

class MyData {
    synchronized public void display(String str) {

            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                try{Thread.sleep(100);}catch(Exception e){}
            }

    }
}

class Mythread1 extends Thread{
    MyData d;// it's like referencing a new variable int d
    public Mythread1(MyData d)// it's like passing an argument
    {
        this.d=d;
    }

    public void run(){
        d.display("hello ");
    }
}

class Mythread2 extends Thread{
    MyData d;// it's like referencing a new variable int d
    public Mythread2(MyData d)// it's like passing an argument
    {
        this.d=d;
    }

    public void run(){
        d.display("world");
    }
}


public class SynchronizationDemo {

    public static void main(String[] args) {
MyData data= new MyData();

Mythread1 t1= new Mythread1(data);
        Mythread2 t2= new Mythread2(data);

        t1.start();
        t2.start();

    }
}
