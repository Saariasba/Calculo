package softbox.com.catex;

public class CalculoRapidoNuevoItem {
    private int boton;
    private String valor;
    private String porcentaje;

    public CalculoRapidoNuevoItem() {
        this.valor = null;
        this.porcentaje = null;
        this.boton = R.drawable.basura;
    }

    public int getBoton() {
        return boton;
    }

    public void setBoton(int boton) {
        this.boton = boton;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
}
