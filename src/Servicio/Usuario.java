package Servicio;

import DAO.TipoUsuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Usuario extends Persona{
    TipoUsuario tipoUsuario;
    List<Libro> prestados;
    Usuario (String nif, String nombre){
        super(nif, nombre);
        tipoUsuario= TipoUsuario.NORMAL;
        prestados=new ArrayList<>();
    }
    @Override
    public String toString(){
        String aux=nif+","+nombre;
        for(Libro l:prestados)
            aux+=","+l.getTitulo();
        return aux;
    }
    public boolean anadirLibro(Libro l) {
        boolean ok=false;
        if (prestados.size()<tipoUsuario.valor) {
            prestados.add(l);
            ok = true;
        }
        return ok;
    }
    public boolean tieneLibro(Libro l) {
        if (prestados.contains(l))
            return true;
        return false;
    }
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
