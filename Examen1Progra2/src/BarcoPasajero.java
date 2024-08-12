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
    public int contadorPasajeros;

    public BarcoPasajero(String nombre, int capacidad, double precioBoleto) {
        super(nombre);
        this.pasajeros = new String[capacidad];
        this.precioBoleto = precioBoleto;
        this.contadorPasajeros = 0;
    }

    public void agregarElemento() {
        if (contadorPasajeros < pasajeros.length) {
            String nombrePasajero = null;
            boolean nombreValido = false;

            while (!nombreValido) {
                nombrePasajero = JOptionPane.showInputDialog("Ingrese el nombre del pasajero:");

                if (nombrePasajero == null || nombrePasajero.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre del pasajero no puede estar vacío.");
                } else if (!nombrePasajero.matches("[a-zA-Z\\s]+")) {
                    JOptionPane.showMessageDialog(null, "El nombre del pasajero no puede contener números.");
                } else {
                    nombreValido = true;
                }
            }

            pasajeros[contadorPasajeros] = nombrePasajero;
            contadorPasajeros++;
        } else {
            JOptionPane.showMessageDialog(null, "El barco está lleno.");
            return;  
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

    public int getContadorPasajeros() {
        return contadorPasajeros;
    }

    
}
