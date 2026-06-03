package DAO;

import Servicio.Libro;
import Servicio.Persona;
import Servicio.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOBibliotecaImpl implements DAOBiblioteca {
    private final static String FICHLIBROS = "libros.txt";
    private List<Libro> libros = new ArrayList<>();
    private Map<String, Persona> personas = new HashMap<>();

    @Override
    public void anadirLibro(Libro l) {
        libros.add(l);
    }

    @Override
    public List<Libro> obtenerLibros() {
        return libros;
    }

    @Override
    public Libro obtenerLibroPorSignatura(int signatura) {
        for (Libro l : libros) {
            if (l.getSignatura() == signatura) {
                return l;
            }
        }
        return null;
    }

    @Override
    public void guardarLibros() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHLIBROS))) {
            for (Libro l : libros) {
                bw.write(l.getSignatura() + ";" + l.getTitulo() + ";" + l.getNumEjemplares());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error en el fichero de libros");
        }
    }

    @Override
    public void leerLibros() {
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(FICHLIBROS))) {
            while (br.ready()) {
                linea = br.readLine();
                String[] campos = linea.split(";");
                int signatura = Integer.parseInt(campos[0]);
                String titulo = campos[1];
                int numEjemplares = Integer.parseInt(campos[2]);
                Libro l = new Libro(signatura, titulo, numEjemplares);
                libros.add(l);
                if (signatura > Libro.NUMLIBROS) {
                    Libro.NUMLIBROS = signatura;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el fichero de libros");
        }
    }

    @Override
    public void altaUsuario(String nif, String nombre) {
        if (!personas.containsKey(nif)) {
            personas.put(nif, new Usuario(nif, nombre));
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        for (Persona p : personas.values()) {
            if (p instanceof Usuario) {
                listaUsuarios.add((Usuario) p);
            }
        }
        return listaUsuarios;
    }

    @Override
    public Usuario obtenerUsuarioPorNif(String nif) {
        Persona p = personas.get(nif);
        if (p != null && p instanceof Usuario) {
            return (Usuario) p;
        }
        return null;
    }

    @Override
    public void borrarUsuario(String nif) {
        personas.remove(nif);
    }
}
