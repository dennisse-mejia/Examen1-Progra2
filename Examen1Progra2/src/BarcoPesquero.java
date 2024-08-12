/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dennisse
 */
public final class BarcoPesquero extends Barco {

    private int pecesCapturados;
    private final TipoPesquero tipoPesquero;

    public BarcoPesquero(String nombre, TipoPesquero tipoPesquero) {
        super(nombre);
        this.pecesCapturados = 0;
        this.tipoPesquero = tipoPesquero;
    }

    public void agregarElemento() {
        pecesCapturados++;
    }

    public double vaciarCobrar() {
        double total = pecesCapturados * tipoPesquero.price;
        pecesCapturados = 0;
        return total;
    }

    public double precioElemento() {
        return tipoPesquero.price;
    }

    public String toString() {
        return super.toString() + " [Tipo: " + tipoPesquero + ", Peces Capturados: " + pecesCapturados + "]";
    }
}
