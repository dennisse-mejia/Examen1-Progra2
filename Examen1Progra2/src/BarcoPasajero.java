/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

/**
 *
 * @author dennisse
 */
public final class BarcoPasajero extends Barco {

    private final String[] pasajeros;
    private final double precioBoleto;
    private int contadorPasajeros;

    public BarcoPasajero(String nombre, int capacidad, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[capacidad];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    public void agregarElemento() {
        if (contadorPasajeros < pasajeros.length) {
            String nombrePasajero = JOptionPane.showInputDialog("Ingrese el nombre del pasajero:");
            pasajeros[contadorPasajeros] = nombrePasajero;
            contadorPasajeros++;
        } else {
            JOptionPane.showMessageDialog(null, "El barco está lleno.");
        }
    }

    public double vaciarCobrar() {
        double total = contadorPasajeros * precioBoleto;
        listarPasajeros();
        contadorPasajeros = 0;
        return total;
    }

    public double precioElemento() {
        return precioBoleto;
    }

    public String toString() {
        return super.toString() + " [Cantidad de Pasajeros que compraron boleto: " + contadorPasajeros + "]";
    }

    public void listarPasajeros() {
        listarPasajerosRecursivo(0);
    }

    private void listarPasajerosRecursivo(int index) {
        if (index < contadorPasajeros) {
            System.out.println(pasajeros[index]);
            listarPasajerosRecursivo(index + 1);
        }
    }
}
