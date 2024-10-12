package kiosko;
import java.util.Queue;

import des.EstadoDelSistema;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class EstadoDelSistemaKiosco extends EstadoDelSistema {
    private Queue<Cliente> colaClientes;
    private List<Empleado> empleados;
    public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}


	private double tiempoTotalClientes;
    private int clientesAtendidos;
    private double beneficios;

    public EstadoDelSistemaKiosco(int numEmpleados) {
        colaClientes = new LinkedList<>();
        empleados = new ArrayList<>(numEmpleados);
        for (int i = 0; i < numEmpleados; i++) {
            empleados.add(new Empleado());
        }
        this.tiempoTotalClientes = 0;
        this.clientesAtendidos = 0;
        this.beneficios = 0;
    }

    public void encolarCliente(Cliente cliente) {
        colaClientes.add(cliente);
    }
    
    public Cliente desencolarCliente() {
        return colaClientes.poll();
    }

    public Empleado obtenerEmpleadoLibre() {
        for (Empleado empleado : empleados) {
            if (empleado.estaLibre()) {
                return empleado;
            }
        }
        return null;
    }

    public void clienteAtendido(Cliente cliente, double tiempoAtencion, double costo, double precioVenta) {
        beneficios += (precioVenta - costo) * cliente.getCantidadArticulos();
        tiempoTotalClientes += (tiempoAtencion + (cliente.getTiempoLlegada() - tiempoAtencion));
        clientesAtendidos++;
    }

    // Getters para las métricas finales

    public double getBeneficios() {
    	System.out.println("IMPRIMIR BENEFICIOS:" + beneficios);
        return beneficios;
    }

    public double getTiempoPromedioClientes() {
        return tiempoTotalClientes / clientesAtendidos;
    }

    public int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public double longitudPromedioCola() {
        return colaClientes.size();
    }
    
    public Queue<Cliente> getColaClientes() {
        return colaClientes;  // Este método devuelve la cola de clientes
    }
    
    public void actualizarServidorDisponible() {
        // Iterar sobre la lista de empleados
        for (Empleado empleado : empleados) {
            // Si el empleado está ocupado
            if (!empleado.estaLibre()) {
                // Aquí puedes marcar al empleado como libre
                empleado.liberar(0); // Liberar al empleado (si es necesario, reiniciar el tiempo de ocupado)
            }
        }
    }
    

	@Override
	public void inicializar() {
	    colaClientes.clear(); // Vaciar la cola de clientes
	    for (Empleado empleado : empleados) {
	        // Aquí podemos restablecer el estado de los empleados si es necesario
	        empleado.liberar(0); // aseguramos que ningún empleado tenga cliente asignado
	        empleado.acumularTiempoLibre(0); // reiniciar tiempo libre si lo manejas por sesión
	    }
	    this.tiempoTotalClientes = 0;
	    this.clientesAtendidos = 0;
	    this.beneficios = 0;
	}
}