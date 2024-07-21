/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cajeroautomatico;

/**
 *
 * @author isabr
 */



public class CajeroAutomatico {
    public static void main(String[] args) {
        Cajero caja = new Cajero();
        Cliente1 cliente1 = new Cliente1(caja);
        Cliente2 cliente2 = new Cliente2(caja);
        Thread c1 = new Thread(cliente1);
        Thread c2 = new Thread(cliente2);
        c1.start();
        c2.start();
        System.out.println("Todos los hilos iniciados");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cliente1.stop();
        cliente2.stop();
        try {
            c1.join();
            c2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Todos los hilos detenidos");
        System.exit(0);
    }
}
