/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author isabr
 */


import java.util.concurrent.Semaphore;

public class Cajero {
    private Semaphore puede_retirar;
    private Semaphore puede_ingresar;
    private Semaphore mutex;
    private int cantidad;

    public Cajero() {
        puede_retirar = new Semaphore(0);
        puede_ingresar = new Semaphore(0);
        mutex = new Semaphore(1);
        cantidad = 10000;
        System.out.println("Cantidad original: " + cantidad);
    }

    public void retirar(int x) {
        try {
            mutex.acquire();
            if (cantidad - x < 0) {
                mutex.release();
                puede_ingresar.acquire();
            } else {
                cantidad -= x;
                System.out.println("Retirando: " + x + " cantidad actual: " + cantidad);
                puede_retirar.release();
                mutex.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ingresar(int x) {
        try {
            mutex.acquire();
            if (cantidad + x > 10000) {
                mutex.release();
                puede_retirar.acquire();
            } else {
                cantidad += x;
                System.out.println("Ingresando: " + x + " cantidad actual: " + cantidad);
                puede_ingresar.release();
                mutex.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
