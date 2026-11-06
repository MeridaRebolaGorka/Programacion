public class Socio {

    //ATRIBUTOS PRIVADOS

    public String nombre;
    public int numeroSocio;
    public int contrasena;
    public int librosPrestados;
    public final int maxPrestamos = 2;

    //CONSTRUCTORES

    public Socio(String nombre, int numeroSocio, int contrasena) {
        this(nombre, numeroSocio, contrasena, 0);
    }

    public Socio(String nombre, int numeroSocio, int contrasena, int librosPrestados) {
        this.nombre = nombre;
        this.numeroSocio = numeroSocio;
        this.contrasena = contrasena;
        this.librosPrestados = librosPrestados;
    }

    boolean correcto = true;

    //MÉTODOS
    public boolean comprobarContrasena(int pin) {
        return this.contrasena == pin;
    }

    public boolean puedePedir() {
        return this.librosPrestados < maxPrestamos;
    }

    public void pedirLibro(Libro libro) {
        if (!puedePedir()) {
            System.out.println("El socio ha alcanzado el límite de préstamos.");
            return;
        }

        boolean exitoPrestamo = Biblioteca.prestar(libro);
        if (exitoPrestamo) {
            this.librosPrestados++;
            System.out.println("Préstamo realizado: ahora tienes \"" + libro.getTitulo() + "\".");
        } else {
            System.out.print("El libro ya está prestado.");
        }
    }

public void devolverLibro(Libro libro) {
    if (this.librosPrestados > 0) {
        Biblioteca.devolver(libro);
        this.librosPrestados--;
        System.out.println("Has devuelto \"" + libro.getTitulo() + "\" correctamente.");
    } else {
        System.out.println("No tienes ningún libro prestado para devolver.");
    }
}

public void mostrarDatos() {
    System.out.println("--- DATOS DEL SOCIO ---");
    System.out.println("Nombre: " + nombre);
    System.out.println("Número de Socio: " + numeroSocio);
    System.out.println("Libros prestados actualmente: " + librosPrestados);
}

   //SETTERS Y GETTERS

public String getNombre() {
    return nombre;
}

   public void setNombre(String nombre) {
    this.nombre = nombre;
   }

   public int getNumeroSocio() {
    return numeroSocio;
   }

   public void setNumeroSocio(int numeroSocio) {
    this.numeroSocio = numeroSocio;
   }

   public int getContrasena() {
    return contrasena;
   }

   public void setContrasena(int contrasena) {
    this.contrasena = contrasena;
   }

   public int getLibrosPrestados() {
    return librosPrestados;
   }

   public void setLibrosPrestados(int librosPrestados) {
    this.librosPrestados = librosPrestados;
   }
}
