package DAO;

import Servicio.Libro;
import Servicio.Usuario;
import java.util.List;

public interface DAOBiblioteca {
    void anadirLibro(Libro l);
    List<Libro> obtenerLibros();
    Libro obtenerLibroPorSignatura(int signatura);
    void guardarLibros();
    void leerLibros();

    void altaUsuario(String nif, String nombre);
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorNif(String nif);
    void borrarUsuario(String nif);
}
