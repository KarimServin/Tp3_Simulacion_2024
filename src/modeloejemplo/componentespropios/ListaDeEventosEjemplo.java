package modeloejemplo.componentespropios;

import java.util.LinkedList;

import des.Evento;
import des.LibreriaDeRutinas;
import des.ListaDeEventos;
import des.RelojDeSimulacion;
import modeloejemplo.eventos.EventoArribarACola;

public class ListaDeEventosEjemplo extends ListaDeEventos {

	public void inicializar(LibreriaDeRutinas libreria, RelojDeSimulacion reloj) {
		lista = new LinkedList<Evento>();
		LibreriaDeRutinasEjemplo libreriaEjemplo = (LibreriaDeRutinasEjemplo) libreria;
		Evento primerEvento = new EventoArribarACola(libreriaEjemplo.tiempoEntreArribosSolicitudes());		
		agregar(primerEvento);
	}
	
}
