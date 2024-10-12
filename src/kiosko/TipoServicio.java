package kiosko;

import modeloejemplo.componentespropios.*;
import java.util.Random;

public enum TipoServicio {
    BEBIDAS(600, 1200, 2.4), // precio compra, precio venta, tiempo medio servicio
    PANADERIA(400, 850, 3.5);  // precio compra, precio venta, tiempo medio servicio

    private final int costo;
    private final int precioVenta;
    private final double tiempoMedioServicio;
    private static final Random random = new Random();

    TipoServicio(int costo, int precioVenta, double tiempoMedioServicio) {
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.tiempoMedioServicio = tiempoMedioServicio;
    }

    // Métodos para obtener los valores
    public int getCosto() {
        return costo;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public double getTiempoMedioServicio() {
    	
        return tiempoMedioServicio; //Antes devolvía un generarTiempoServicioAleatorio
        
    }

    // Método para generar un tiempo de servicio aleatorio basado en la distribución exponencial
    //private double generarTiempoServicioAleatorio(double media) {

      //  double u = random.nextDouble(); // Genera número aleatorio entre 0 y 1
        //double LAMBDA = 1 / media;   // Lambda es el inverso de la media
        //System.out.println(-Math.log(1 - u) / LAMBDA);
        
       // return -Math.log(1 - u) / LAMBDA; // Genera el valor de tiempo aleatorio con distribución exponencial
    //}
    
    public int getParametroTipo(TipoServicio unTipo) {
        return (unTipo == TipoServicio.BEBIDAS) ? 1 : 2;
    }
    
    
    
}