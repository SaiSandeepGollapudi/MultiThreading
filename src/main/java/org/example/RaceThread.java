package org.example;


class WhiteBoard {
    String currentText;
    int numberOfStudents = 0;
    int readCount = 0;

    public void markAttendance() {
        numberOfStudents++;
    }

    //  method to write text on the whiteboard
    synchronized public void write(String text) {
        System.out.println("Teacher is Writing: " + text);

        // Wait until all students have read the previous text
        while (readCount != 0)
            try {wait();} catch (Exception e) {}

        // Set the new text on the whiteboard
        currentText = text;
        // Update the read count to match the number of students
        readCount = numberOfStudents;
        // Notify all students that new text is available
        notifyAll();
    }

    //  method to read text from the whiteboard
    synchronized public String read() {
        // Wait until there is text written on the whiteboard
        while (readCount == 0)
            try {wait();} catch (Exception e) {}

        // Get the text from the whiteboard
        String text = currentText;
        // Decrease the read count as the student has read the text
        readCount--;
        // Notify the teacher if all students have read the text
        if (readCount == 0)
            notify();
        return text;
    }
}

class Teacher extends Thread {
    WhiteBoard whiteBoard;

    String notes[] = {"Java is a language", "It is Object-Oriented", "It is Platform Independent", "It supports multithreading", "end"};

    public Teacher(WhiteBoard whiteBoard) {
        this.whiteBoard = whiteBoard;
    }

    public void run() {
        // Write each note on the whiteboard
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            whiteBoard.write(note);
        }

    }
}

class Student extends Thread {
    String name;
    WhiteBoard whiteBoard;

    public Student(String name, WhiteBoard whiteBoard) {
        this.name = name;
        this.whiteBoard = whiteBoard;
    }

    public void run() {
        String text;
        // Mark attendance
        whiteBoard.markAttendance();
        do {
            // Read text from the whiteboard
            text = whiteBoard.read();
            System.out.println(name + " Reading: " + text);
            System.out.flush();
        } while (!text.equals("end"));
    }
}

public class RaceThread {
    public static void main(String[] args) {
        WhiteBoard whiteBoard = new WhiteBoard();
        Teacher teacher = new Teacher(whiteBoard);

        Student student1 = new Student("1. John", whiteBoard);
        Student student2 = new Student("2. Ajay", whiteBoard);
        Student student3 = new Student("3. Kloob", whiteBoard);
        Student student4 = new Student("4. Smith", whiteBoard);

        teacher.start();

        student1.start();
        student2.start();
        student3.start();
        student4.start();
    }
}


