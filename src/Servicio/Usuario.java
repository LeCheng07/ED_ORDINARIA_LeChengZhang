/**
 * @author LeCheng Zhang
 * @version version 3 comentarios javadoc
 */

package Servicio;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Persona{
    TipoUsuario tipoUsuario;
    List<Libro> prestados;
    public Usuario(String nif, String nombre){
        super(nif, nombre);
        tipoUsuario= TipoUsuario.NORMAL;
        prestados=new ArrayList<>();
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Libro> getPrestados() {
        return prestados;
    }

    public void setPrestados(List<Libro> prestados) {
        this.prestados = prestados;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        String aux=nif+","+nombre;
        for(Libro l:prestados)
            aux+=","+l.getTitulo();
        return aux;
    }
    /**
     * Añade un libro a la lista de libros prestados del usuario si no supera el límite permitido por su tipo de usuario.
     *
     * @param l El libro que se desea añadir.
     * @return true si el libro se añade correctamente; false en caso contrario.
     */
    public boolean anadirLibro(Libro l) {
        boolean ok=false;
        if (prestados.size()<tipoUsuario.valor) {
            prestados.add(l);
            ok = true;
        }
        return ok;
    }

    /**
     * Comprueba si el usuario tiene prestado un libro concreto.
     *
     * @param l El libro a buscar.
     * @return true si el usuario tiene el libro; false en caso contrario.
     */
    public boolean tieneLibro(Libro l) {
        if (prestados.contains(l))
            return true;
        return false;
    }

    /**
     * Elimina un libro de la lista de libros prestados del usuario comparando su signatura.
     *
     * @param l El libro que se desea eliminar.
     * @return true al finalizar el proceso de eliminación.
     */
    public boolean eliminarLibro(Libro l) {
        boolean encontrado=false;
        for (int i=0; i<prestados.size() && !encontrado;i++) {
            if( l.getSignatura()==prestados.get(i).getSignatura()) {
                prestados.remove(i);
                encontrado = true;
            }
        }
       // prestados.remove(l);
        return true;
    }
}
