/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tegarnization
 */
public class Carousel {
    private static int jumlah;
    private static int posisi;

    public int getJumlah() {
        return jumlah;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setPosisi(int posisi) {
        Carousel.posisi = posisi;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    
    
}
