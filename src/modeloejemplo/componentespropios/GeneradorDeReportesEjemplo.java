package modeloejemplo.componentespropios;

import des.ContadoresEstadisticos;
import des.GeneradorDeReportes;
import kiosko.EstadoDelSistemaKiosco; // Asegúrate de importar tu clase de modelo
import kiosko.Empleado;

public class GeneradorDeReportesEjemplo extends GeneradorDeReportes {

    private EstadoDelSistemaKiosco modeloKiosco; // Agregar un campo para mantener el modelo

    public GeneradorDeReportesEjemplo(EstadoDelSistemaKiosco modeloKiosco) {
        this.modeloKiosco = modeloKiosco; // Inicializa el modelo
    }

    public void run(ContadoresEstadisticos contadores) {
        
        ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;

        System.out.println("------------------------------------------------------");
        System.out.println("*** GENERADOR DE REPORTES *** ");
        System.out.println("------------------------------------------------------");

        System.out.println("La cantidad de solicitudes procesadas es de: " + contadoresEjemplo.getCantProcesadas());
        System.out.println("Los beneficios totales obtenidos son: $" + contadoresEjemplo.getBeneficiosTotales());

        // Longitud promedio de la cola de clientes
        double longitudPromedioCola = modeloKiosco.longitudPromedioCola();
        System.out.println("La longitud promedio de clientes en cola es: " + longitudPromedioCola);

        // Tiempo promedio de los clientes en el kiosco
        double tiempoPromedioClientes = contadoresEjemplo.getTiempoPromedioClientes();
        System.out.println("El tiempo promedio que los clientes pasan en el kiosco es: " + tiempoPromedioClientes + " minutos");

        // Tasa de atención (clientes por hora) por empleada
        for (Empleado empleado : modeloKiosco.getEmpleados()) {
            double tasaAtencionPorEmpleado = contadoresEjemplo.getCantProcesadas() / 8.0 ; // considerando que son 8 horas
            System.out.println("La tasa de atención (clientes por hora) del empleado es: " + tasaAtencionPorEmpleado);
           
            double porcentajeTiempoLibre = (empleado.getTiempoLibre() / (empleado.getTiempoOcupado() + empleado.getTiempoLibre())) * 100; // Tiempo libre en porcentaje
            System.out.println("Porcentaje de tiempo libre del empleado: " + porcentajeTiempoLibre + "%");
        }
    }
}
