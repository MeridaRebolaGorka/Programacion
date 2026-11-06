public class Libro {

    //ATRIBUTOS PRIVADOS

    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private int vecesPrestado;

    //CONSTRUCTORES

    public Libro(String titulo, String autor, int anioPublicacion) {
        this(titulo, autor, anioPublicacion, true, 0);
    }

    public Libro(String titulo, String autor, int anioPublicacion, boolean disponible, int vecesPrestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = disponible;
        this.vecesPrestado = vecesPrestado;
    }

    // MÉTODOS


public void mostrarInfo() {
        String estado = disponible ? "Disponible" : "Prestado";
        System.out.println(titulo + " - " + autor + " (" + anioPublicacion + ")");
        System.out.println("   Estado: " + estado);
    }

    //SETTERS Y GETTERS

public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }
}
