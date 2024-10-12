package modeloejemplo.componentespropios;

import java.util.Random;

import des.LibreriaDeRutinas;

/* Subprogramas usados para generar observaciones aleatorias desde las distribuciones de 
 * probabilidad asociadas al modelo. */

public class LibreriaDeRutinasEjemplo extends LibreriaDeRutinas {

	private static final double MEDIA = 4; 
	
	
	public double tiempoEntreArribosSolicitudes() {
		// Aca se debería programar el método de generación de variables que corresponde.
		return (double) generarVariableAleatoriaExponencial();
	}

	public int tiempoDeProcesamiento() {
		// Aca se debería programar el método de generación de variables que corresponde.
		return (int) generarVariableAleatoriaExponencial();
	}
	
	
	
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
    
    
    /**
     * Genera una variable aleatoria que sigue una distribución exponencial
     * utilizando el método de la transformada inversa.
     *
     * @return un valor que representa la variable aleatoria generada
     */
    public double generarVariableAleatoriaExponencial() {
        double u = generarNumeroAleatorio(); // Número aleatorio entre 0 y 1
        
        double LAMBDA = 1/MEDIA;
        
        return -Math.log(1 - u) / LAMBDA; // Transformada inversa para la distribución exponencial
        
	
    }
    
    
    
    public int generarCantidadArticulosBebidas() {
        double random = generarNumeroAleatorio(); // Genera un número entre 0 y 1
        if (random < 0.57) {
            return 1; // 57% de probabilidad
        } else if (random < 0.90) { // 57% + 33% = 90%
            return 2; // 33% de probabilidad
        } else {
            return 3; // 10% de probabilidad
        }
    }

    /**
     * Genera la cantidad de artículos que un cliente comprará de panadería
     * basándose en las probabilidades.
     * - 1 artículo: 27%
     * - 2 artículos: 25%
     * - 3 artículos: 35%
     * - 4 artículos: 13%
     */
    public int generarCantidadArticulosPanaderia() {
        double random = generarNumeroAleatorio(); // Genera un número entre 0 y 1
        if (random < 0.27) {   
            return 1; // 27% de probabilidad
        } else if (random < 0.52) { // 27% + 25% = 52%
            return 2; // 25% de probabilidad
        } else if (random < 0.87) { // 52% + 35% = 87%
            return 3; // 35% de probabilidad
        } else {
            return 4; // 13% de probabilidad
        }
    }
    
    public double generarVariableAleatoriaExponencialConParametro(double media) {
        double u = generarNumeroAleatorio(); // Número aleatorio entre 0 y 1
        
        double LAMBDA = 1/media;
        
        return -Math.log(1 - u) / LAMBDA; // Transformada inversa para la distribución exponencial
        
	
    }
	
	

}
