package modeloejemplo.estadodelsistema;

/* Servidor en la nube. */

public class Servidor {

	Boolean estaOcupado; /* false = libre / true = ocupado */
	Solicitud solicitudEnProcesamiento; /* Solicitud que está siendo retenida en el servidor. */

	public Servidor(boolean estado) {
		super();
		estaOcupado = estado;
		solicitudEnProcesamiento = null;
	}

	public boolean getEstaOcupado() {
		return estaOcupado;
	}

	public void pasarAOcupado(Solicitud solicitud) {
		estaOcupado = true;
		solicitudEnProcesamiento = solicitud;
	}

	public void setEstaOcupado(boolean estado) {
		estaOcupado = estado;
	}
	
}
