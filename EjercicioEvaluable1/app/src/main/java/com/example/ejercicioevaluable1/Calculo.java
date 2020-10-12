package com.example.ejercicioevaluable1;

public class Calculo {

    public static int getPrimeraPosicionVacia(int[] valores){
        int posicion=0;
       while(valores[posicion] != 0 && posicion <100) {
           posicion++;
       }
       return posicion;
    }

    public static int[] calcularHasta(int[] valores, int posicionIntroducida) {
        int ultimaPosicionACalcular = posicionIntroducida - 1;
        int i = getPrimeraPosicionVacia(valores);
        while (i <= ultimaPosicionACalcular) {
            valores[i] = (i + 1) * 2;
            i++;
        }
        return valores;
    }
}
