import java.util.InputMismatchException;
import java.util.Scanner;

public class Biblioteca {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. Preparación de Libros
        Libro libro1 = new Libro("El Hobbit", "Tolkien", 1937);
        Libro libro2 = new Libro("Dune", "Frank Herbert", 1965);
        Libro libro3 = new Libro("Momo", "Michael Ende", 1973);

        // 2. Preparación de Socios
        Socio socio1 = new Socio("Lucía", 101, 1234);
        Socio socio2 = new Socio("Marcos", 102, 5678);

        // --- INICIO DE SESIÓN ---
        System.out.println("--- SOCIOS ---");
        System.out.println(socio1.getNumeroSocio() + " - " + socio1.getNombre());
        System.out.println(socio2.getNumeroSocio() + " - " + socio2.getNombre());

        Socio socioActivo = null;
        System.out.print("Introduce tu número de socio: ");
        
        // 3. Bucle para verificar existencia del socio
        while (socioActivo == null) {
            int numIntroducido = lectura();
            if (numIntroducido == socio1.getNumeroSocio()) {
                socioActivo = socio1;
            } else if (numIntroducido == socio2.getNumeroSocio()) {
                socioActivo = socio2;
            } else {
                System.out.print("Ese socio no existe, inténtalo de nuevo: ");
            }
        }

        // 4. Bucle para verificar contraseña (3 intentos)
        int intentos = 3;
        boolean accesoConcedido = false;
        
        while (intentos > 0 && !accesoConcedido) {
            System.out.print("Contraseña: ");
            int pin = lectura();
            
            if (socioActivo.comprobarContrasena(pin)) {
                accesoConcedido = true;
                System.out.println("Bienvenida/o, " + socioActivo.getNombre());
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("Contraseña incorrecta. Te quedan " + intentos + " intentos.");
                } else {
                    System.out.println("Acceso denegado. Has agotado tus intentos.");
                    return; // Termina la ejecución del programa
                }
            }
        }

        // --- MENÚ PRINCIPAL (do-while) ---
        int opcion = 0;
        do {
            System.out.println("\n1. Ver todos los libros");
            System.out.println("2. Pedir un libro");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Ver mis datos de socio");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = lectura();

            switch (opcion) {
                case 1:
                    mostrarTodos(libro1, libro2, libro3);
                    break;
                case 2:
                    mostrarTodos(libro1, libro2, libro3);
                    System.out.print("¿Qué libro quieres? ");
                    int numPedido = pedirNumeroLibroValido();
                    Libro libroAPedir = elegirLibro(numPedido, libro1, libro2, libro3);
                    socioActivo.pedirLibro(libroAPedir);
                    break;
                case 3:
                    mostrarTodos(libro1, libro2, libro3);
                    System.out.print("¿Qué libro quieres devolver? ");
                    int numDevuelto = pedirNumeroLibroValido();
                    Libro libroADevolver = elegirLibro(numDevuelto, libro1, libro2, libro3);
                    socioActivo.devolverLibro(libroADevolver);
                    break;
                case 4:
                    socioActivo.mostrarDatos();
                    break;
                case 5:
                    // Opción de salida (se maneja al terminar el bucle)
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);

        // --- AL SALIR ---
        // 8. Libros sin devolver
        System.out.println("\nTe quedan " + socioActivo.getLibrosPrestados() + " libros sin devolver.");

        // 9. Cálculo del libro más prestado (Comparativa con if/else)
        int p1 = libro1.getVecesPrestado();
        int p2 = libro2.getVecesPrestado();
        int p3 = libro3.getVecesPrestado();

        if (p1 == 0 && p2 == 0 && p3 == 0) {
            System.out.println("No se ha prestado ningún libro en esta sesión.");
        } else if (p1 > p2 && p1 > p3) {
            System.out.println("El libro más prestado ha sido \"" + libro1.getTitulo() + "\" (" + p1 + " veces).");
        } else if (p2 > p1 && p2 > p3) {
            System.out.println("El libro más prestado ha sido \"" + libro2.getTitulo() + "\" (" + p2 + " veces).");
        } else if (p3 > p1 && p3 > p2) {
            System.out.println("El libro más prestado ha sido \"" + libro3.getTitulo() + "\" (" + p3 + " veces).");
        } else {
            System.out.println("Ha habido un empate entre los libros más prestados.");
        }

        System.out.println("¡Hasta pronto!");
    }

    // --- MÉTODOS AUXILIARES OBLIGATORIOS ---

    // Cambia el estado del libro si está libre
    public static boolean prestar(Libro libro) {
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            libro.setVecesPrestado(libro.getVecesPrestado() + 1);
            return true;
        }
        return false;
    }

    // Libera el libro
    public static void devolver(Libro libro) {
        libro.setDisponible(true);
    }

    // Lector seguro con try/catch para evitar caídas por strings
    public static int lectura() {
        while (true) {
            try {
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer del scanner
                return numero;
            } catch (InputMismatchException e) {
                System.out.print("Error: Debes introducir un número entero. Inténtalo de nuevo: ");
                scanner.nextLine(); // Limpiar el buffer incorrecto
            }
        }
    }

    // Imprime la lista completa usando mostrarInfo()
    public static void mostrarTodos(Libro l1, Libro l2, Libro l3) {
        System.out.println("\n --- LIBROS DE LA BIBLIOTECA ---");
        System.out.print("1. "); l1.mostrarInfo();
        System.out.print("2. "); l2.mostrarInfo();
        System.out.print("3. "); l3.mostrarInfo();
    }

    // Devuelve el objeto correspondiente al índice numérico
    public static Libro elegirLibro(int num, Libro l1, Libro l2, Libro l3) {
        if (num == 1) return l1;
        else if (num == 2) return l2;
        else return l3;
    }

    // Método de soporte interno para obligar a elegir entre 1, 2 o 3
    private static int pedirNumeroLibroValido() {
        int num = lectura();
        while (num < 1 || num > 3) {
            System.out.print("Número no válido (Elige 1, 2 o 3): ");
            num = lectura();
        }
        return num;
    }
}
