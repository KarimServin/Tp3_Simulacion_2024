package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;

/* Variables que almacenan información estadística referida al comportamiento del sistema. */
public class ContadoresEstadisticosEjemplo extends ContadoresEstadisticos {

    private int cantSolicitudesProcesadas;
    private double beneficiosTotales; // Almacena los beneficios totales generados
    private double tiempoTotalClientes; // Para calcular el tiempo promedio
   

    public ContadoresEstadisticosEjemplo() {
        super();
    }

    public void inicializar() {
        cantSolicitudesProcesadas = 0;
        beneficiosTotales = 0.0;
        tiempoTotalClientes = 0.0;
        
    }

    public int getCantProcesadas() {
        return cantSolicitudesProcesadas;
    }

    public void actualizarCantProcesadas() {
        cantSolicitudesProcesadas++;
    }

    public double getBeneficiosTotales() {
        return beneficiosTotales;
    }

    public void agregarBeneficios(double cantidad) {
        beneficiosTotales = cantidad; // Sumar los beneficios generados
    }

    public double getTiempoTotalClientes() {
        return tiempoTotalClientes;
    }

    public void agregarTiempoTotal(double tiempo) {
        tiempoTotalClientes += tiempo; // Sumar el tiempo total que los clientes pasaron en el kiosco
    }

    

    

    // Calcula el tiempo promedio de los clientes en el kiosco
    public double getTiempoPromedioClientes() {
    	System.out.println(tiempoTotalClientes);
    	
        return (cantSolicitudesProcesadas == 0) ? 0 : tiempoTotalClientes / cantSolicitudesProcesadas;
    }
}
