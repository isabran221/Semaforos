/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author isabr
 */


public class Cliente2 implements Runnable {
    private Cajero caja;
    private volatile boolean running = true;

    public Cliente2(Cajero caja) {
        this.caja = caja;
    }

    public void run() {
        while (running) {
            int cantidad = (int) (Math.random() * 3000) + 1;
            System.out.println("Intentando ingresar: " + cantidad);
            caja.ingresar(cantidad);
            try {
                Thread.sleep(100);
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
