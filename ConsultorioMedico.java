/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package consultoriomedico;

/**
 *
 * @author isabr
 */


import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class ConsultorioMedico extends JFrame {
    private JTextArea displayArea;
    private Semaphore salaEspera;
    private Semaphore recepcionista;

    public ConsultorioMedico() {
        salaEspera = new Semaphore(10); // Capacidad de la sala de espera
        recepcionista = new Semaphore(1); // Solo una recepcionista

        setTitle("Consultorio Médico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton nuevoPacienteButton = new JButton("Nuevo Paciente");
        nuevoPacienteButton.addActionListener(e -> nuevoPaciente());
        add(nuevoPacienteButton, BorderLayout.SOUTH);
    }

    private void nuevoPaciente() {
        new Thread(new Paciente()).start();
    }

    private class Paciente implements Runnable {
        @Override
        public void run() {
            try {
                salaEspera.acquire();
                mostrarMensaje("Paciente en sala de espera.");
                Thread.sleep((long) (Math.random() * 2000 + 1000));
                recepcionista.acquire();
                mostrarMensaje("Paciente agendando cita con la recepcionista.");
                Thread.sleep((long) (Math.random() * 2000 + 1000));
                recepcionista.release();
                salaEspera.release();
                mostrarMensaje("Paciente atendido y se retira.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> displayArea.append(mensaje + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultorioMedico frame = new ConsultorioMedico();
            frame.setVisible(true);
        });
    }
}
