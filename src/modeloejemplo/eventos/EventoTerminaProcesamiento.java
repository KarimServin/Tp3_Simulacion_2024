package modeloejemplo.eventos;

import des.ContadoresEstadisticos;
import des.EstadoDelSistema;
import des.Evento;
import des.ListaDeEventos;
import kiosko.Cliente;
import kiosko.Empleado;
import kiosko.EstadoDelSistemaKiosco;
import kiosko.TipoServicio;
import modeloejemplo.componentespropios.ContadoresEstadisticosEjemplo;
import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;
import modeloejemplo.estadodelsistema.ModeloDelEjemplo;
import modeloejemplo.estadodelsistema.Solicitud;

public class EventoTerminaProcesamiento extends Evento {
    private Empleado empleado; // Se almacena el empleado que estaba atendiendo
    private Cliente cliente; // Se almacena el cliente que fue atendido

    public EventoTerminaProcesamiento(double tiempoDeOcurrencia, Empleado empleado, Cliente cliente) {
        super(tiempoDeOcurrencia);
        this.empleado = empleado;
        this.cliente = cliente;
    }

    @Override
    public void rutinaDeEvento(EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos,
                               LibreriaDeRutinasEjemplo libreria) {
        ContadoresEstadisticosEjemplo contadoresEjemplo = (ContadoresEstadisticosEjemplo) contadores;
        contadoresEjemplo.actualizarCantProcesadas();
        
        EstadoDelSistemaKiosco estadoKiosco = (EstadoDelSistemaKiosco) modelo; // Correcto cast a EstadoDelSistemaKiosco
        contadoresEjemplo.agregarBeneficios(estadoKiosco.getBeneficios()); //medio fulero pq se va actualizando siempre
        
        // Aquí se registran métricas
        double tiempoServicio = cliente.getTiempoServicio();
        estadoKiosco.clienteAtendido(cliente, tiempoServicio, cliente.getTipoServicio().getCosto(), cliente.getTipoServicio().getPrecioVenta());
        contadoresEjemplo.agregarTiempoTotal(getTiempoDeOcurrencia()-cliente.getTiempoLlegada());
        //System.out.println(getTiempoDeOcurrencia()-cliente.getTiempoLlegada());
        // Liberar al empleado y actualizar estado
        empleado.liberar(tiempoServicio);
        

        // Si hay clientes en espera, deberán ser atendidos
        if (estadoKiosco.getColaClientes().size() > 0) {
            Cliente siguienteCliente = estadoKiosco.desencolarCliente();
            empleado.atenderCliente(siguienteCliente);

            // Calcular el tiempo de servicio para el siguiente cliente
            double tiempoServicioBase = siguienteCliente.getTipoServicio().getTiempoMedioServicio();
            double tiempoAdicional = calcularTiempoAdicional(siguienteCliente);
            double tiempoTotalServicio = tiempoServicioBase + tiempoAdicional;
            siguienteCliente.setTiempoServicio(tiempoTotalServicio);
            

            // Programar el nuevo evento
            EventoTerminaProcesamiento nuevoEvento = new EventoTerminaProcesamiento(getTiempoDeOcurrencia() + tiempoTotalServicio, empleado, siguienteCliente);
            eventos.agregar(nuevoEvento);
            
        } else {
            empleado.liberar(tiempoServicio); // Asegurarse de liberar si no hay más clientes
            estadoKiosco.actualizarServidorDisponible(tiempoServicio);
            
        }
        
    }

    private double calcularTiempoAdicional(Cliente cliente) {
        int cantidad = cliente.getCantidadArticulos();
        double tiempoBase = cliente.getTipoServicio().getTiempoMedioServicio();
        
        

        if (cliente.getTipoServicio() == TipoServicio.BEBIDAS) {
            if (cantidad == 2) return 0.1 * tiempoBase;
            if (cantidad == 3) return 0.13 * tiempoBase;
        } else if (cliente.getTipoServicio() == TipoServicio.PANADERIA) {
            if (cantidad == 2) return 0.12 * tiempoBase;
            if (cantidad == 3) return 0.15 * tiempoBase;
            if (cantidad == 4) return 0.20 * tiempoBase;
        }
        return 0;
    }
}