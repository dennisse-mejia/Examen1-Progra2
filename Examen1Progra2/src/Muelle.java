/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dennisse
 */
public class Muelle {

    private final List<Barco> barcos;
    private JTextArea infoBarcoTextArea;

    public Muelle() {
        barcos = new ArrayList<>();
        infoBarcoTextArea = new JTextArea(10, 30);
        infoBarcoTextArea.setEditable(false);
    }

    public void agregarBarco(String tipo) {
        if (tipo == null) {
            return;
        }

        tipo = tipo.trim().toUpperCase();
        if (!tipo.equals("PESQUERO") && !tipo.equals("PASAJERO")) {
            JOptionPane.showMessageDialog(null, "Tipo de barco no reconocido.");
            return;
        }

        String nombre = null;
        boolean nombreValido = false;

        while (!nombreValido) {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del barco:");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre del barco no puede estar vacío.");
            } else if (!nombre.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, "El nombre del barco solo puede contener letras y espacios.");
            } else {
                nombreValido = true;
            }
        }

        for (Barco barco : barcos) {
            if (barco.getNombre().equals(nombre)) {
                JOptionPane.showMessageDialog(null, "El nombre del barco ya existe.");
                return;
            }
        }

        Barco nuevoBarco = null;
        if (tipo.equals("PESQUERO")) {
            String tipoPesquero = JOptionPane.showInputDialog("Ingrese el tipo de pesquero (PEZ, CAMARON, LANGOSTA):");
            if (tipoPesquero == null) {
                return;
            }
            TipoPesquero tipoPesqueroEnum;
            try {
                tipoPesqueroEnum = TipoPesquero.valueOf(tipoPesquero.toUpperCase());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Tipo de pesquero no reconocido.");
                return;
            }
            nuevoBarco = new BarcoPesquero(nombre, tipoPesqueroEnum);
        } else if (tipo.equals("PASAJERO")) {
            int capacidad;
            double precioBoleto;
            try {
                capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del barco:"));
                precioBoleto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del boleto:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida para capacidad o precio del boleto.");
                return;
            }
            nuevoBarco = new BarcoPasajero(nombre, capacidad, precioBoleto);
        }

        if (nuevoBarco != null) {
            barcos.add(nuevoBarco);
            infoBarcoTextArea.append("Último barco agregado:\n" + nuevoBarco.toString() + "\n\n");
            JOptionPane.showMessageDialog(null, new JScrollPane(infoBarcoTextArea));
        }
    }

    public void agregarElemento(String nombre) {
        if (nombre == null) {
            return;
        }

        Barco barco = buscarBarco(nombre);
        if (barco != null) {
            int pasajerosAntes = 0;

            if (barco instanceof BarcoPasajero) {
                pasajerosAntes = ((BarcoPasajero) barco).getContadorPasajeros();
            }

            barco.agregarElemento();

            if (barco instanceof BarcoPesquero) {
                infoBarcoTextArea.append("Peces capturados aumentados en 1 para el barco: " + nombre + "\n\n");
                JOptionPane.showMessageDialog(null, "Peces capturados aumentados en 1 para el barco: " + nombre);
            } else if (barco instanceof BarcoPasajero) {
                int pasajerosDespues = ((BarcoPasajero) barco).getContadorPasajeros();
                if (pasajerosDespues > pasajerosAntes) {
                    infoBarcoTextArea.append("Pasajero agregado al barco: " + nombre + "\n\n");
                    JOptionPane.showMessageDialog(null, "Pasajero agregado al barco: " + nombre);
                }
            }

            infoBarcoTextArea.append(barco.toString() + "\n\n");
        } else {
            JOptionPane.showMessageDialog(null, "Barco no encontrado.");
        }
    }

    public double vaciarBarco(String nombre) {
        Barco barco = buscarBarco(nombre);
        if (barco != null) {
            String infoAntesDeVaciado = barco.toString();

            double total = barco.vaciarCobrar();

            String vaciadoInfo = "Barco vaciado: " + infoAntesDeVaciado + "\nTotal generado: " + total + "\n\n";
            infoBarcoTextArea.append(vaciadoInfo);
            JOptionPane.showMessageDialog(null, vaciadoInfo);

            if (barco instanceof BarcoPasajero) {
                ((BarcoPasajero) barco).listarPasajeros();
            }
            return total;
        } else {
            JOptionPane.showMessageDialog(null, "Barco no encontrado.");
            return 0;
        }
    }

    public void barcosDesde(int year) {
        barcosDesdeRecursivo(0, year);
    }

    private void barcosDesdeRecursivo(int index, int year) {
        if (index < barcos.size()) {
            Barco barco = barcos.get(index);
            if (barco.getFechaCirculacion().getYear() >= year) {
                String barcoInfo = "Nombre Barco: " + barco.getNombre() + " - Fecha: " + barco.getFechaCirculacion() + "\n\n";
                infoBarcoTextArea.append(barcoInfo);
                JOptionPane.showMessageDialog(null, barcoInfo);
            }
            barcosDesdeRecursivo(index + 1, year);
        }
    }

    private Barco buscarBarco(String nombre) {
        for (Barco barco : barcos) {
            if (barco.getNombre().equalsIgnoreCase(nombre)) {
                return barco;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Muelle muelle = new Muelle();
        String[] options = {"Agregar Barco", "Agregar Elemento", "Vaciar Barco", "Barcos Desde Año", "Salir"};
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(null, muelle.getPanelWithTextArea(), "Muelle",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (choice) {
                case 0:
                    String tipo = JOptionPane.showInputDialog("Ingrese el tipo de barco (PESQUERO o PASAJERO):");
                    muelle.agregarBarco(tipo);
                    break;
                case 1:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del barco:");
                    muelle.agregarElemento(nombre);
                    break;
                case 2:
                    String nombreVacio = JOptionPane.showInputDialog("Ingrese el nombre del barco:");
                    muelle.vaciarBarco(nombreVacio);
                    break;
                case 3:
                    int year = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año:"));
                    muelle.barcosDesde(year);
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

    private JPanel getPanelWithTextArea() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Información de Barcos:"));
        panel.add(new JScrollPane(infoBarcoTextArea));
        return panel;
    }
}
