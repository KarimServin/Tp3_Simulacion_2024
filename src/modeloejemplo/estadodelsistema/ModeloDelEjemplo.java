package modeloejemplo.estadodelsistema;

import des.EstadoDelSistema;

/* Colección de variables de estado necesarias para describir  el sistema en un punto en el tiempo. */

public class ModeloDelEjemplo extends EstadoDelSistema {
	
	private ColaDeSolicitudes cola;
	private Servidor servidor;
	
	public void inicializar() {
		cola = new ColaDeSolicitudes();
		servidor = new Servidor(false);
	}

	public void encolarSolicitud(Solicitud solicitudParaAgregar) {		
		System.out.println("\t\t-- El MODELO encola una solicitud de la clase " + solicitudParaAgregar.getClase() + " ya que el servidor está ocupado.");
		cola.encolarSolicitud(solicitudParaAgregar);
	}

	public boolean estaServidorOcupado() {
		return servidor.getEstaOcupado();
	}

	public void atenderSolicitud(Solicitud solicitudParaAgregar) {
		System.out.println("\t\t-- El MODELO atiende una solicitud de la clase " + solicitudParaAgregar.getClase() + ".");
		servidor.pasarAOcupado(solicitudParaAgregar);
	}

	public boolean haySolicitudesEnEspera() {
		return (cola.getCantSolicitudesEsperando()>0);
	}

	public Solicitud obtenerSolicitudPrioritaria() {
		return cola.solicitudPrioritaria();
	}

	public void actualizarServidorDisponible() {
		System.out.println("\t\t-- El MODELO deja al servidor disponible ya que no hay solicitudes en espera.");
		servidor.setEstaOcupado(false);
	}
	
}
