package kiosko;

public class Cliente {
    private TipoServicio tipoServicio;
    private int cantidadArticulos;
    private double tiempoLlegada;
    private double tiempoServicio;
    private double tiempoEspera; 

    public Cliente(TipoServicio tipoServicio, int cantidadArticulos, double tiempoLlegada) {
        this.tipoServicio = tipoServicio;
        this.cantidadArticulos = cantidadArticulos;
        this.tiempoLlegada = tiempoLlegada;
    }
    // Getters y setters

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public double getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(double tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }
    
    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(double tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }
    
    
}