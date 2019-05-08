/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author Emma
 */
public class HoraActual extends Thread{

    private JLabel jl = new JLabel();
    private String tiempo;
    private int hora;
    private int minutos;
    private int segundos;

    public HoraActual() {
    }

    public JLabel getJl() {
        return jl;
    }

    public void setJl(JLabel jl, int h, int m, int s) {
        this.jl = jl;
        hora = h;
        minutos = m;
        segundos = s;
    }

    public void TiempoReal() {
        try {
            while(segundos >= 0 && minutos >= 0 && hora >= 0) {
                if(segundos < 60){
                    sleep(1000);
                    segundos = segundos + 1;
                    String sec = ((segundos<10)? ("0"+String.valueOf(segundos)) : String.valueOf(segundos));
                    String min = ((minutos<10)? ("0"+String.valueOf(minutos)) : String.valueOf(minutos));
                    String hor = ((hora<10)? ("0"+String.valueOf(hora)) : String.valueOf(hora));
                    jl.setText( hor + ":" + min  + ":" + sec);
                }
                else {
                    if(minutos < 60){

                        minutos = minutos + 1;
                        segundos = 0;

                        String sec = ((segundos<10)? ("0"+String.valueOf(segundos)) : String.valueOf(segundos));
                        String min = ((minutos<10)? ("0"+String.valueOf(minutos)) : String.valueOf(minutos));
                        String hor = ((hora<10)? ("0"+String.valueOf(hora)) : String.valueOf(hora));
                        jl.setText( hor + ":" + min  + ":" + sec);
                    }
                    else{
                        if(hora < 24){
                            hora = hora + 1;
                            minutos = 0;
                            if (hora >= 24)
                                hora = 0;
                            String sec = ((segundos<10)? ("0"+String.valueOf(segundos)) : String.valueOf(segundos));
                            String min = ((minutos<10)? ("0"+String.valueOf(minutos)) : String.valueOf(minutos));
                            String hor = ((hora<10)? ("0"+String.valueOf(hora)) : String.valueOf(hora));
                            jl.setText( hor + ":" + min  + ":" + sec);
                        }
                    }

                }

                if (hora >= 23 && minutos >= 59 && segundos>=60)
                {
                   hora = 0;
                   minutos = 0;
                   segundos = 0;
                   String sec = ((segundos<10)? ("0"+String.valueOf(segundos)) : String.valueOf(segundos));
                   String min = ((minutos<10)? ("0"+String.valueOf(minutos)) : String.valueOf(minutos));
                   String hor = ((hora<10)? ("0"+String.valueOf(hora)) : String.valueOf(hora));
                   jl.setText( hor + ":" + min  + ":" + sec);
                }                        
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(hora >= 0 && minutos >= 0 && segundos >= 0) {
            TiempoReal();
            }
    }
    
}
