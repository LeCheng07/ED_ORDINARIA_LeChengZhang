package UI;

import Servicio.Libro;
import Servicio.Usuario;
import Servicio.Biblioteca;
import java.util.List;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    static void menu(Biblioteca b) {
        int opc=0;
        System.out.println("Servicio.Biblioteca Principal");
        do {
            System.out.println("1-Dar alta libro");
            System.out.println("2-Dar alta usuario");
            System.out.println("3-Listar libros");
            System.out.println("4-Listar usuarios");
            System.out.println("5-Prestar Libro");
            System.out.println("6-Devolver Libro");
            System.out.println("7-Usuarios que poseen 1 libro concreto");
            System.out.println("8-Eliminar usuario");
            System.out.println("0-fin" );
            try {
                opc = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Debe introducir un NÚMERO de opción");
            }
            switch (opc) {
                case 1-> altaLibro(b);
                case 2-> altaUsuario(b);
                case 3-> listarLibros(b);
                case 4-> listarUsuarios(b);
                case 5-> prestarLibros(b);
                case 6-> devolverLibro(b);
                case 7-> listarLibroPrestado(b); //indicar los usuarios que tienen un libro dado
                case 8-> eliminarUsuario(b);
                case 0-> System.out.println("Fin");
                default -> System.out.println("Error. Opción no válida");
            }
        }while (opc!=0);
    }
    private static void eliminarUsuario(Biblioteca b) {
        System.out.println("nif usuario");
        String nif = sc.nextLine();
        Usuario u = b.getUsuario(nif);
        if (u != null) {
            b.borrarUsuario(u);
        }
    }
    private static void listarLibroPrestado(Biblioteca b) {
        System.out.println("signatura libro");
        int signatura = Integer.parseInt(sc.nextLine());
        Libro l = b.getLibro(signatura);
        if (l != null) {
           List<Usuario>listaUsuarios= b.usuariosConLibro(l);
           if (listaUsuarios!=null) {
               for (Usuario u:listaUsuarios) {
                   System.out.println(u.nif+"->"+u.nombre);
               }
           }
           else
            System.out.println("Ningún usuario tiene ese libro");
        }
        else System.out.println("El libro no existe");
    }
    private static void devolverLibro(Biblioteca b) {
        System.out.println("nif usuario");
        String nif = sc.nextLine();
        Usuario u = b.getUsuario(nif);
        if (u != null) {
            System.out.println("signatura libro");
            int signatura = Integer.parseInt(sc.nextLine());
            Libro l = b.getLibro(signatura);
            b.devolverLibro(u,l);
        }
    }

    private static void prestarLibros(Biblioteca b) {
        System.out.println("nif usuario");
        String nif=sc.nextLine();
        Usuario u= b.getUsuario(nif);
        if (u!=null){
            System.out.println("signatura libro");
            int signatura=Integer.parseInt(sc.nextLine());
            Libro l=b.getLibro(signatura);
            if (l!=null && l.getNumEjemplares()>0){
                b.prestarLibro(u, l);  //decrementar ejemplares disponibles
            }
            else System.out.println("Libro no disponible");
        }
        else System.out.println("Usuario no existe");

    }
    private static void listarUsuarios(Biblioteca b) {
        List<Usuario> listaUsuarios= b.listarUsuarios();
        for (Usuario u:listaUsuarios){
            System.out.println(u);
        }
        //deben mostrarse los libros prestados
    }
    private static void altaUsuario(Biblioteca b) {
        System.out.println("nif:");
        String nif = sc.nextLine();
        System.out.println("Nombre");
        String nombre = sc.nextLine();
        b.altaUsuario(nif,nombre);
    }
    private static void listarLibros(Biblioteca b) {
        List<Libro> listaLibros= b.todosLosLibros();
        for (Libro l:listaLibros)
            System.out.println(l);
    }
    private static void altaLibro(Biblioteca b) {
        System.out.println("Título");
        String titulo = sc.nextLine();
        System.out.println("Num Ejemplares");
        int numEjemplares = Integer.parseInt(sc.nextLine());
        Libro l= new Libro(titulo, numEjemplares);
        b.anadirLibro(l);
    }
    public static void main(String[] args) {
            Biblioteca b=new Biblioteca();
            leerLibros(b);
            menu(b);
            guardarLibros(b);
            
    }
    

    private static void leerLibros(Biblioteca b) {
        b.leerLibros();
    }

    private static void guardarLibros(Biblioteca b) {
        b.guardarLibros();
    }
}
