package Mision;

public class ContraReloj {
    private long tiempoInicio;
    private long tiempoTranscurrido;
    private long limite;
    private boolean contando = false;
    
    public void iniciar(long Limit) {
        if (!contando) {
            tiempoInicio = System.currentTimeMillis() - tiempoTranscurrido;
            limite = Limit;
            contando = true;
        }
    }

    public void detener() {
        if (contando) {
            tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
            contando = false;
        }
    }

    public long obtenerTiempo() {
        return contando
            ? System.currentTimeMillis() - tiempoInicio
            : tiempoTranscurrido;
    }
    public String info() {
        long tiempo = obtenerTiempo();
        long minutos = (tiempo / 60000) % 60;
        long segundos = (tiempo / 1000) % 60;
        long centesimas = (tiempo % 1000) / 10;
        return String.format("%02d:%02d.%02d", minutos, segundos, centesimas);
    }

    public boolean estaContando() {
        return contando;
    }
    public boolean exceso(){
        return obtenerTiempo() > limite;
    }
    public void reiniciar() {
        tiempoInicio = 0;
        tiempoTranscurrido = 0;
        limite = 0;
        contando = false;
    }
    public long TimeRest(){
        return Math.max(0, limite - obtenerTiempo());
    }
}

