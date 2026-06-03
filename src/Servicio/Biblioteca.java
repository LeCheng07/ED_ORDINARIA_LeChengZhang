package Servicio;

import DAO.DAOBiblioteca;
import DAO.DAOBibliotecaImpl;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private DAOBiblioteca dao;

    public Biblioteca(){
        this.dao = new DAOBibliotecaImpl();
    }     
    public void anadirLibro(Libro l) {
        dao.anadirLibro(l);
    }
    public List<Libro> todosLosLibros() {
        return dao.obtenerLibros();
    }
    public void altaUsuario(String nif, String nombre) {
        dao.altaUsuario(nif, nombre);
    }
    public List<Usuario> listarUsuarios() {
        return dao.obtenerUsuarios();
    }
    public Usuario getUsuario(String nif) {
        return dao.obtenerUsuarioPorNif(nif);
    }
    public Libro getLibro(int signatura) {
        return dao.obtenerLibroPorSignatura(signatura);
    }
    public boolean prestarLibro(Usuario u, Libro l) {
        boolean ok=true;
        if (u.anadirLibro(l))
            l.restarEjemplaresDisponibles();
        else
            ok=false;
        return ok;
    }
    public boolean devolverLibro(Usuario u, Libro l) {
        if (u.tieneLibro(l)) {
            if (u.eliminarLibro(l))
                l.sumarEjemplar();
        } else {
            return false;
        }
        return true;
    }

    public List<Usuario> usuariosConLibro(Libro l) {
        List<Usuario> lista=new ArrayList<>();
        for (Usuario u : dao.obtenerUsuarios()) {
            if (u.tieneLibro(l)) {
                lista.add(u);
            }
        }
        return lista;
    }
    public void borrarUsuario(Usuario u) {
        dao.borrarUsuario(u.nif);
    }

    public void guardarLibros() {
        dao.guardarLibros();
    }
    public void leerLibros() {
        dao.leerLibros();
    }
}
