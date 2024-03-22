package org.example;

class SharedData {
    int value;
    boolean isProduced;

    public SharedData() {
        isProduced = false; // Initially, data is not produced
    }

    // producer sets data
    synchronized public void produce(int newValue) {
        // Wait until consumer consumes the previous data
        while (isProduced) {
            try {
                wait(); // Wait until notified by consumer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Set the new value
        value = newValue;
        // Set flag to indicate data is produced
        isProduced = true;
        // Notify consumer that data is available
        notify();
    }

    synchronized public int consume() {
        int consumedValue = 0;
        // Wait until producer produces the data
        while (!isProduced) {
            try {
                wait(); // Wait until notified by producer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Get the value
        consumedValue = value;
        // Set flag to indicate data is consumed
        isProduced = false;
        // Notify producer that data is consumed
        notify();

        return consumedValue;
    }
}

class Producer extends Thread {
    SharedData sharedData;

    public Producer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        int count = 1;
        while (true) {
            // Produce new data value
            sharedData.produce(count);
            System.out.println("Produced: " + count);
            count++;
        }
    }
}

class Consumer extends Thread {
    SharedData sharedData;

    public Consumer(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        int consumedValue;
        while (true) {
            // Consume data value
            consumedValue = sharedData.consume();
            System.out.println("Consumed: " + consumedValue);
        }
    }
}

public class InterThread {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Producer producer = new Producer(sharedData);
        Consumer consumer = new Consumer(sharedData);

        producer.start();
        consumer.start();
    }
}

