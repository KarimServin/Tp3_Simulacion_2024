package modeloejemplo.componentespropios;

import java.util.Random;

import des.LibreriaDeRutinas;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	private static final double MEDIA = 4; 
	private long[] semillas;
	
	
	public LibreriaDeRutinasEjemplo( ) {
		
		semillas = new long[5];
		semillas[0] = 9834; //TiempoEntreArribos
        semillas[1] = 9159; //TiempoBaseServicioBebidas
        semillas[2] = 5443; //TiempoBaseServicioPanaderia
        semillas[3] = 6572; //TipoServicio
        semillas[4] = 7489; //CantidadArticulos	
	}
	
	public double tiempoEntreArribosSolicitudes() {
		// Aca se debería programar el método de generación de variables que corresponde.
		return (double) generarVariableAleatoriaExponencialConMedia();
	}

	//public int tiempoDeProcesamiento() {
		// Aca se debería programar el método de generación de variables que corresponde.
	//	return (int) generarVariableAleatoriaExponencial();
	//}
	
	
	
	public static long generarSemillaAleatoria() {
        Random random = new Random();
        int randomInt = random.nextInt(); // Generar número entero aleatorio
        //double randomDouble = (double) randomInt; // Convertir a double
        
        //long numero = random.nextInt(10000) + 1000;
        
        //System.out.println(numero);
        
        
        return random.nextInt(10000) + 1000; // Genera una semilla entre 1 y 10,000
        
    }

    public static double generarNumeroAleatorio() {
        int cantidadDigitos = 4; // Longitud fija de 4 dígitos

        long semilla = generarSemillaAleatoria();
        // Calculamos el cuadrado de la semilla
        long cuadrado = semilla * semilla;

        // Convertimos a cadena para extraer los dígitos centrales
        String cuadradoStr = String.format("%0" + (cantidadDigitos * 2) + "d", cuadrado);
        int longitud = cuadradoStr.length();

        // Extraemos los dígitos centrales
        int inicio = (longitud - cantidadDigitos) / 2;
        String digitosCentros = cuadradoStr.substring(inicio, inicio + cantidadDigitos);

        // Actualizamos la semilla para el siguiente uso
        semilla = Long.parseLong(digitosCentros);
        
        // Retornamos el número entre 0 y 1
        return (double) semilla / Math.pow(10, cantidadDigitos);
    }
    
    public double generarNumeroAleatorioConParametro(int parametro) {
        //El metodo ya actualiza la semilla en funcion al parámetro
    	int cantidadDigitos = 4; // Longitud fija de 4 dígitos

        long semilla = semillas[parametro];
        // Calculamos el cuadrado de la semilla
        long cuadrado = semilla * semilla;

        // Convertimos a cadena para extraer los dígitos centrales
        String cuadradoStr = String.format("%0" + (cantidadDigitos * 2) + "d", cuadrado);
        int longitud = cuadradoStr.length();

        // Extraemos los dígitos centrales
        int inicio = (longitud - cantidadDigitos) / 2;
        String digitosCentros = cuadradoStr.substring(inicio, inicio + cantidadDigitos);

        // Actualizamos la semilla para el siguiente uso
        semillas[parametro] = Long.parseLong(digitosCentros);
        
        // Retornamos el número entre 0 y 1
        return (double) semilla / Math.pow(10, cantidadDigitos);
    }
    
    
    public double generarVariableAleatoriaExponencialConMedia() {
        double u = generarNumeroAleatorioConParametro(0); // Número aleatorio entre 0 y 1
        
        double LAMBDA = 1/MEDIA;
        
        return -Math.log(1 - u) / LAMBDA; // Transformada inversa para la distribución exponencial
        
	
    }
  
    
    public double generarVariableAleatoriaExponencial(int parametroTipo, double unaMedia) {
        double u = generarNumeroAleatorioConParametro(parametroTipo); // Número aleatorio entre 0 y 1
        
        double LAMBDA = 1/unaMedia;
        
        return -Math.log(1 - u) / LAMBDA; // Transformada inversa para la distribución exponencial
        
	
    }
    
    
    public int generarCantidadArticulosBebidas() {
        double random = generarNumeroAleatorioConParametro(4); // Genera un número entre 0 y 1
        if (random < 0.57) {
            return 1; // 57% de probabilidad
        } else if (random < 0.90) { // 57% + 33% = 90%
            return 2; // 33% de probabilidad
        } else {
            return 3; // 10% de probabilidad
        }
    }

    
    public int generarCantidadArticulosPanaderia() {
        double random = generarNumeroAleatorioConParametro(4); // Genera un número entre 0 y 1
        if (random < 0.27) {   
            return 1; // 27% de probabilidad
        } else if (random < 0.52) { 
            return 2; // 25% de probabilidad
        } else if (random < 0.87) { 
            return 3; // 35% de probabilidad
        } else {
            return 4; // 13% de probabilidad
        }   
    }
    
	

}