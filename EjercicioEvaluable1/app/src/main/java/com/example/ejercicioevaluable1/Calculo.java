package com.example.ejercicioevaluable1;

public class Calculo {

    public static void calcularFibo(double[] valores, int posicionCalcu, int posicionIntroducida) {
        int posicion = posicionCalcu;
        while(posicion <= posicionIntroducida) {
            valores[posicion] = valores[posicion -1] + valores[posicion -2];
            posicion ++;
        }
    }

}
