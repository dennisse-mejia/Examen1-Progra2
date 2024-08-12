/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.time.LocalDate;

/**
 *
 * @author dennisse
 */
public abstract class Barco {

    private final String nombre;
    private final LocalDate fechaCirculacion;

    public Barco(String nombre) {
        this.nombre = nombre;
        this.fechaCirculacion = LocalDate.now();
    }

    public final String getNombre() {
        return nombre;
    }

    public final LocalDate getFechaCirculacion() {
        return fechaCirculacion;
    }

    public String toString() {
        return "Barco: " + nombre;
    }

    public abstract void agregarElemento();

    public abstract double vaciarCobrar();

    public abstract double precioElemento();
}
