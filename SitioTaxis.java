/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sitiotaxis;

/**
 *
 * @author isabr
 */

import java.util.concurrent.Semaphore;

public class SitioTaxis {
    private Semaphore taxisDisponibles;
    private Semaphore mutex;

    public SitioTaxis() {
        taxisDisponibles = new Semaphore(4); // Capacidad de pasajeros por taxi
        mutex = new Semaphore(1); // Control de partida de taxis
    }

    public void asignarTaxi(Pasajero pasajero) {
        try {
            taxisDisponibles.acquire();
            System.out.println(pasajero.getNombre() + " asignado a un taxi.");
            taxisDisponibles.release();

            mutex.acquire();
            System.out.println("Taxi partiendo con " + pasajero.getNombre());
            Thread.sleep(30000); // Esperar 30 segundos antes de permitir otro taxi partir
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SitioTaxis sitio = new SitioTaxis();
        for (int i = 1; i <= 10; i++) {
            Pasajero pasajero = new Pasajero("Pasajero " + i);
            new Thread(() -> sitio.asignarTaxi(pasajero)).start();
        }
    }
}

class Pasajero {
    private String nombre;

    public Pasajero(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

