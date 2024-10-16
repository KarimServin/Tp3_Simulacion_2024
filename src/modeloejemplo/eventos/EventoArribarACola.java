package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import des.RelojDeSimulacion;
import kiosko.Cliente;
import kiosko.Empleado;
import kiosko.EstadoDelSistemaKiosco;
import kiosko.TipoServicio;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoArribarACola extends Evento {

	public EventoArribarACola(double tiempoDeOcurrencia) {
		super(tiempoDeOcurrencia);
	}

	@Override
	public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
	                           LibreriaDeRutinasEjemplo libreria) {

	    EstadoDelSistemaKiosco estadoKiosco = (EstadoDelSistemaKiosco) modelo;

	    
	    TipoServicio tipoServicio = libreria.generarNumeroAleatorioConParametro(3) < 0.7 ? TipoServicio.BEBIDAS : TipoServicio.PANADERIA;
	    
	    int cantidadArticulos = (tipoServicio == TipoServicio.BEBIDAS) ? libreria.generarCantidadArticulosBebidas() : libreria.generarCantidadArticulosPanaderia();
	    Cliente cliente = new Cliente(tipoServicio, cantidadArticulos, getTiempoDeOcurrencia());
	    
	    
	    double tiempoServicioBase = libreria.generarVariableAleatoriaExponencial(getParametroTipo(cliente.getTipoServicio()), cliente.getTipoServicio().getTiempoMedioServicio());
	    double tiempoAdicional = calcularTiempoAdicional(cliente,tiempoServicioBase);
	    cliente.setTiempoServicio(tiempoServicioBase + tiempoAdicional);

	    // Agregar cliente a la cola
	    estadoKiosco.encolarCliente(cliente);

	    // Programar el siguiente arribo
	    double proximoArribo = libreria.tiempoEntreArribosSolicitudes();
	    EventoArribarACola nuevoEvento = new EventoArribarACola(getTiempoDeOcurrencia() + proximoArribo);    
	    eventos.agregar(nuevoEvento);

	    // Intentar iniciar la atención si hay empleados disponibles
	    Empleado empleado = estadoKiosco.obtenerEmpleadoLibre();
	    if (empleado != null) {
	    	
	        empleado.atenderCliente(cliente); // Atención al cliente
	        //double tiempoServicioBase = libreria.generarVariableAleatoriaExponencial(getParametroTipo(cliente.getTipoServicio()), cliente.getTipoServicio().getTiempoMedioServicio());
	       
	        /*
	         * El tiempo servicio base se calcula en base a una variable exponencial
	         * Se le pasa el parámetro del tipo, 1-> bebidas, 2-> panaderia
	         * y el tiempo medio servicio
	         */
	        //double tiempoAdicional = calcularTiempoAdicional(cliente,tiempoServicioBase);
	        //double tiempoTotalServicio = tiempoServicioBase + tiempoAdicional;
	        //cliente.setTiempoServicio(tiempoTotalServicio);
	        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

	        // Programar el evento de finalización del procesamiento
	        EventoTerminaProcesamiento nuevoEventoTermina = new EventoTerminaProcesamiento(getTiempoDeOcurrencia() + cliente.getTiempoServicio(), empleado, cliente);
	        eventos.agregar(nuevoEventoTermina);
	    }
	    
	    
	}
	
	private int getParametroTipo(TipoServicio unTipo) {
		//Sirve para pasarle a la libreria el tipo de parametro, y asi obtener y modificar la semilla
		
		return (unTipo == TipoServicio.BEBIDAS) ? 1 : 2;
	}

	private double calcularTiempoAdicional(Cliente cliente, double tiempoBase) {
	    int cantidad = cliente.getCantidadArticulos();
	    

	    // Calcular el tiempo adicional según el tipo de servicio y la cantidad de artículos
	    if (cliente.getTipoServicio() == TipoServicio.BEBIDAS) {
	        switch (cantidad) {
	            case 2:
	                return 0.1 * tiempoBase; // 10%
	            case 3:
	                return 0.13 * tiempoBase; // 13%
	            default:
	                return 0; // Para 1 artículo, no hay tiempo adicional
	        }
	    } else if (cliente.getTipoServicio() == TipoServicio.PANADERIA) {
	        switch (cantidad) {
	            case 2:
	                return 0.12 * tiempoBase; // 12%
	            case 3:
	                return 0.15 * tiempoBase; // 15%
	            case 4:
	                return 0.20 * tiempoBase; // 20%
	            default:
	                return 0; // Para 1 artículo, no hay tiempo adicional
	        }
	    }
	    return 0; // Por defecto, no hay tiempo adicional
	}
	
}


