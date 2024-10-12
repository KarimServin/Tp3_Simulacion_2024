package des;

import kiosko.EstadoDelSistemaKiosco; // 
import modeloejemplo.eventos.*; //
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.GeneradorDeReportesEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.componentespropios.ListaDeEventosEjemplo;

public class ProgramaPrincipal {

    // Creación de los componentes propios del kiosco.
    private static EstadoDelSistemaKiosco modelo;        
    private static ContadoresEstadisticosEjemplo contadores;
    private static GeneradorDeReportesEjemplo reporte;
    private static LibreriaDeRutinasEjemplo libreria;
    private static ListaDeEventos eventos;

    public static void main(String[] args) {

        System.out.println(LibreriaDeRutinasEjemplo.generarNumeroAleatorio());
        
        // Creación de los componentes del kiosco.
        crearComponentesDependientes();
        
        // Creación del reloj y la lista de eventos
        RelojDeSimulacion reloj = new RelojDeSimulacion();
        RutinaDeTiempo manejoDeTiempo = new RutinaDeTiempo(); // Crea la instancia de RutinaDeTiempo
        eventos = new ListaDeEventosEjemplo(); // Asegúrate de tener esta implementación

        // Inicializar el estado del sistema y los eventos
        modelo.inicializar();
        eventos.inicializar(libreria, reloj); // Debes cumplir con el método inicializar

        // Iniciar con el primer evento de arribo de clientes
        eventos.agregar(new EventoArribarACola(reloj.getValor()));

        System.out.println("------------------------------------------------------");
        System.out.println("*** INICIALIZACIÓN");
        System.out.println("------------------------------------------------------");
        
        // Bucle de control principal
        do { 
            System.out.println("------------------------------------------------------");
            System.out.println("*** PROGRAMA PRINCIPAL *** t=" + reloj.getValor());
            System.out.println("------------------------------------------------------");
                        
            // Invocar a la rutina de tiempo.
            Evento eventoPorEjecutar = manejoDeTiempo.run(eventos, reloj);
            
            System.out.println("\t\t-- El SIMULADOR determina que el EVENTO MÁS INMINENTE se dará en " + eventoPorEjecutar.getTiempoQueFaltaParaQueOcurra() + " unidades de tiempo.");
            System.out.println("\t\t-- El SIMULADOR actualiza el RELOJ para ejecutar el EVENTO MÁS INMINENTE del tipo " + eventoPorEjecutar.getClass().getSimpleName() + ".");
            
            // Invocar a la rutina de evento.
            eventoPorEjecutar.rutinaDeEvento(modelo, contadores, eventos, libreria);
            
        } while (!terminoLaSimulacion(reloj, contadores));
        
        // Generar el reporte al final de la simulación
        reporte.run(contadores);
    }

    private static void crearComponentesDependientes() {
        // Aquí se crean los componentes específicos del kiosco
        modelo = new EstadoDelSistemaKiosco(2); // Ajustar el número de empleados
        contadores = new ContadoresEstadisticosEjemplo();
        reporte = new GeneradorDeReportesEjemplo(modelo);
        libreria = new LibreriaDeRutinasEjemplo();
        eventos = new ListaDeEventosEjemplo();
    }

    private static boolean terminoLaSimulacion(RelojDeSimulacion reloj, ContadoresEstadisticos contadores) {
        // 
        // (aquí se establecen 480 minutos para 8 horas)
        return reloj.getValor() >= 480;
    }
}
