package com.librocatalogo;

import com.librocatalogo.model.Book;
import com.librocatalogo.service.DatabaseService;
import com.librocatalogo.service.GutendexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private GutendexService gutendexService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("¡Bienvenido al Catálogo de Libros!");
            System.out.println("Seleccione una opción:");
            System.out.println("1) Agregar un libro manualmente");
            System.out.println("2) Listar todos los libros");
            System.out.println("3) Buscar libros por idioma");
            System.out.println("4) Buscar y guardar libros desde Gutendex");
            System.out.println("5) Salir");
            System.out.print("Opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    searchBooksByLanguage(scanner);
                    break;
                case 4:
                    searchAndSaveBooks(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void addBook(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el idioma del libro: ");
        String language = scanner.nextLine();
        System.out.print("Ingrese el número de descargas: ");
        int downloadCount = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Book book = new Book();
        book.setTitle(title);
        book.setLanguage(language);
        book.setDownloadCount(downloadCount);

        databaseService.saveBook(book);
        System.out.println("Libro agregado exitosamente.");
    }

    private void listAllBooks() {
        List<Book> books = databaseService.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void searchBooksByLanguage(Scanner scanner) {
        System.out.print("Ingrese el idioma: ");
        String language = scanner.nextLine();
        List<Book> books = databaseService.findByLanguage(language);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void searchAndSaveBooks(Scanner scanner) throws IOException {
        System.out.print("Ingrese el título del libro a buscar: ");
        String title = scanner.nextLine();
        List<Book> books = gutendexService.searchBooksByTitle(title);
        for (Book book : books) {
            databaseService.saveBook(book);
            System.out.println("Libro guardado: " + book);
        }
    }
}
