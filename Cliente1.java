/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author isabr
 */



public class Cliente1 implements Runnable {
    private Cajero caja;
    private volatile boolean running = true;

    public Cliente1(Cajero caja) {
        this.caja = caja;
    }

    public void run() {
        while (running) {
            int cantidad = (int) (Math.random() * 2000) + 1;
            System.out.println("Intentando retirar: " + cantidad);
            caja.retirar(cantidad);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }
}


