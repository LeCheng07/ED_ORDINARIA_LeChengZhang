package Servicio;

import java.util.Objects;


public class Libro {
    int signatura;
    String titulo;
    int numEjemplares;
    public static int NUMLIBROS=0;

    public Libro(int signatura, String titulo, int numEjemplares) {
        this.signatura = signatura;
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
    }

    public Libro(String titulo, int numEjemplares) {
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
        signatura=++NUMLIBROS;
    }

    public int getSignatura() {
        return signatura;
    }

    public void setSignatura(int signatura) {
        this.signatura = signatura;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public void restarEjemplaresDisponibles() {
        if (numEjemplares>0)
          numEjemplares--;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return this.signatura == libro.signatura;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(signatura);
    }

    public void sumarEjemplar() {
        numEjemplares++;
    }
}
