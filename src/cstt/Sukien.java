/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cstt;

/**
 *
 * @author HCB
 */
public class Sukien {
    private String ten, ngunghia;
        public Sukien()
        {
            ten = ngunghia = "";
        }
        public Sukien(String ten, String ngunghia)
        {
            this.ten = ten;
            this.ngunghia = ngunghia;
        }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgunghia() {
        return ngunghia;
    }

    public void setNgunghia(String ngunghia) {
        this.ngunghia = ngunghia;
    }
}
