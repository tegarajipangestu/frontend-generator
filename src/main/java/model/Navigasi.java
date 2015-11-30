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
public class Navigasi {
    
    private String posisi;
    private int jumlahMenu;

    public String getClassName() {
        if ("fixed".equals(posisi)) 
            return "navbar navbar-inverse navbar-fixed-top";
        else
            return "navbar navbar-inverse";            
    }
    
    

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public int getJumlahMenu() {
        return jumlahMenu;
    }

    public void setJumlahMenu(int jumlahMenu) {
        this.jumlahMenu = jumlahMenu;
    }
    
    
}
