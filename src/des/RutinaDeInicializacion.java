package des;

import modeloejemplo.componentespropios.LibreriaDeRutinasEjemplo;

/* Subprograma que inicializa el modelo en t=0.*/

/***************************************/
/* NO MODIFICAR PARA USAR EL SIMULADOR */
/***************************************/

public class RutinaDeInicializacion {
	
	public void run(RelojDeSimulacion reloj, EstadoDelSistema modelo, ContadoresEstadisticos contadores, ListaDeEventos eventos, LibreriaDeRutinasEjemplo libreria) {
		
		//Setear el reloj de simulación en 0.
		reloj.inicializar();
		
		//Inicializar el estado del sistema.
		modelo.inicializar();
		
		//Inicializar  los contadores estadísticos.
		contadores.inicializar();
		
		//Inicializar la lista de eventos.
		eventos.inicializar(libreria,reloj);
		
	}

}
