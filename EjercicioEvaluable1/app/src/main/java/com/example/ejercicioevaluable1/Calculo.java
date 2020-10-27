package com.example.ejercicioevaluable1;

public class Calculo {

    public static void calcularFibo(double[] valores, int ultimaPosicionCalcu, int posicionIntroducida) {
        int pos = ultimaPosicionCalcu;
        while(pos <= posicionIntroducida) {
            valores[pos] = valores[pos -1] + valores[pos -2];
            pos ++;
        }
    }

}
