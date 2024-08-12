/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dennisse
 */
public enum TipoPesquero {
    PEZ(100.0), CAMARON(150.0), LANGOSTA(250.0);

    public final double price;

    TipoPesquero(double price) {
        this.price = price;
    }

}
