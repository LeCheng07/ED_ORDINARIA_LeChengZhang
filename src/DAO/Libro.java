package DAO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Libro {
    int signatura;
    String titulo;
    int numEjemplares;
    static int NUMLIBROS=0;
    Libro(String titulo, int numEjemplares) {
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
        signatura=++NUMLIBROS;
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
