package kiosko;

import kiosko.Cliente;

public class Empleado {
    private boolean estaOcupado;
    private Cliente clienteActual;
    private double tiempoOcupado;
    private double tiempoLibre;

    public Empleado() {
        this.estaOcupado = false;
        this.setClienteActual(null);
        this.setTiempoOcupado(0);
        this.setTiempoLibre(0);
    }

    public boolean estaLibre() {
        return !estaOcupado;
    }

    public void atenderCliente(Cliente cliente) {
        this.estaOcupado = true;
        this.setClienteActual(cliente);
    }

    public void liberar(double tiempoAtendido) {
        this.estaOcupado = false;
        this.setClienteActual(null);
        setTiempoOcupado(getTiempoOcupado() + tiempoAtendido);
    }

    public void acumularTiempoLibre(double tiempoLibre) {
        this.setTiempoLibre(this.getTiempoLibre() + tiempoLibre);
    }

	public double getTiempoLibre() {
		return tiempoLibre;
	}

	public void setTiempoLibre(double tiempoLibre) {
		this.tiempoLibre = tiempoLibre;
	}

	public Cliente getClienteActual() {
		return clienteActual;
	}

	public void setClienteActual(Cliente clienteActual) {
		this.clienteActual = clienteActual;
	}

	public double getTiempoOcupado() {
		return tiempoOcupado;
	}

	public void setTiempoOcupado(double tiempoOcupado) {
		this.tiempoOcupado = tiempoOcupado;
	}
	
	 // Getters para las métricas

	
    
}
