/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author chaconqu
 */
public class FechaIngreso {
    private int IntDia;
    private int IntMes;
    private int IntAno;

    public FechaIngreso(int IntDia, int IntMes, int IntAno) {
        this.IntDia = IntDia;
        this.IntMes = IntMes;
        this.IntAno = IntAno;
    }
    
    public FechaIngreso() {
        this.IntDia = 0;
        this.IntMes = 0;
        this.IntAno = 0;
    }

    public int getIntDia() {
        return IntDia;
    }

    public void setIntDia(int IntDia) {
        this.IntDia = IntDia;
    }

    public int getIntMes() {
        return IntMes;
    }

    public void setIntMes(int IntMes) {
        this.IntMes = IntMes;
    }

    public int getIntAno() {
        return IntAno;
    }

    public void setIntAno(int IntAno) {
        this.IntAno = IntAno;
    }
    
    @Override
    public String toString(){
    return (IntDia+"/"+IntMes+"/"+IntAno); 
        }
    
}
