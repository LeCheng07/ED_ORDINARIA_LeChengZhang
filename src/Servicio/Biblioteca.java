package Servicio;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import DAO.Libro;

public class Biblioteca {
    final static String FICHLIBROS="libros.txt";
    private List<Libro> libros;
    private Map<String, Persona> personas;
    public Biblioteca(){
        libros=new ArrayList<>();
        personas=new HashMap<>();
    }
    public void anadirLibro(Libro l) {
        libros.add(l);
    }
    public List<Libro> todosLosLibros() {
        return libros;
    }
    public void altaUsuario(String nif, String nombre) {
        if (!personas.containsKey(nif))
          personas.put(nif,new Usuario(nif,nombre));
    }
    public List<Usuario> listarUsuarios() {
        List<Usuario> listaUsuarios=new ArrayList<>();
        for (Persona p:personas.values()){
            if (p instanceof Usuario)
                listaUsuarios.add((Usuario)p);
        }
        return listaUsuarios;
    }
    public Usuario getUsuario(String nif) {
        Persona p= personas.get(nif);
        if (p!=null && p instanceof Usuario)
          return (Usuario)p;
        return null;
    }
    public Libro getLibro(int signatura) {
        Libro laux=null;
        for (Libro l:libros)
            if (l.getSignatura()==signatura) {
                laux = l;
                break;
            }
        return laux;
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
        if (u.tieneLibro(l))
            if (u.eliminarLibro(l))
                l.sumarEjemplar();
        else return false;
        return true;
    }

    public List<Usuario> usuariosConLibro(Libro l) {
        List<Usuario> lista=new ArrayList<>();
        for (Persona p:personas.values())
            if (p instanceof Usuario)
                if(((Usuario)p).tieneLibro(l))
                    lista.add((Usuario)p);
        return lista;
    }
    public void borrarUsuario(Usuario u) {
        personas.remove(u.nif);
    }

    public void guardarLibros() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHLIBROS))){
            for (Libro l:libros) {
                bw.write(l.signatura + ";" + l.titulo + ";" + l.getNumEjemplares());
                bw.newLine();
            }
        }catch (Exception e){
            System.out.println("Error en el fichero de libros");
        }
    }
    public void leerLibros() {
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(FICHLIBROS))){
            while (br.ready()){
                linea=br.readLine();
                String[] campos= linea.split(";");
                int signatura=Integer.parseInt(campos[0]);
                String titulo=campos[1];
                int numEjemplares=Integer.parseInt(campos[2]);
                Libro l=new Libro(signatura,titulo,numEjemplares);
                libros.add(l);
            }
        }catch (Exception e){
            System.out.println("Error en el fichero de libros");
        }
    }
}
