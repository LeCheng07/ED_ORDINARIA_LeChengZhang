package DAO;

public enum TipoUsuario {
    NORMAL(2), PREFERENTE(4), SUPER(1000);
    int valor;
    TipoUsuario(int valor) {
        this.valor = valor;
    }
}
