/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stolovay;

import java.util.concurrent.Semaphore;

public class Stolovay {
    private static Semaphore tableSemaphore = new Semaphore(1);
    private static Semaphore studentSemaphore = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 1; i <= 7; i++) {
            final int studentId = i;
            new Thread(() -> {
                try {
                    System.out.println("Student" + studentId + " waiting");
                    tableSemaphore.acquire();
                    studentSemaphore.acquire();
                    System.out.println("Student" + studentId + " eating");
                    Thread.sleep(3000);
                    studentSemaphore.release();
                    System.out.println("Student" + studentId + " exit");
                    tableSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
