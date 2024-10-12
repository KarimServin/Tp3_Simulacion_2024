package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
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

	    // Generar nuevo cliente
	    TipoServicio tipoServicio = libreria.generarNumeroAleatorio() < 0.7 ? TipoServicio.BEBIDAS : TipoServicio.PANADERIA;
	    int cantidadArticulos = (tipoServicio == TipoServicio.BEBIDAS) ? libreria.generarCantidadArticulosBebidas() : libreria.generarCantidadArticulosPanaderia();
	    Cliente cliente = new Cliente(tipoServicio, cantidadArticulos, getTiempoDeOcurrencia());

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
	        double tiempoServicioBase = cliente.getTipoServicio().getTiempoMedioServicio();
	        double tiempoAdicional = calcularTiempoAdicional(cliente);
	        double tiempoTotalServicio = tiempoServicioBase + tiempoAdicional;
	        cliente.setTiempoServicio(tiempoTotalServicio);

	        // Programar el evento de finalización del procesamiento
	        EventoTerminaProcesamiento nuevoEventoTermina = new EventoTerminaProcesamiento(getTiempoDeOcurrencia() + tiempoTotalServicio, empleado, cliente);
	        eventos.agregar(nuevoEventoTermina);
	    }
	}
	
	private double calcularTiempoAdicional(Cliente cliente) {
	    int cantidad = cliente.getCantidadArticulos();
	    double tiempoBase = cliente.getTipoServicio().getTiempoMedioServicio();

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


