package com.libros.reserva_libros.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.libros.reserva_libros.model.Libro;

/**
 * Esta clase testea la lógica del servicio de libros.
 * No necesita base de datos ni web, solo verifica que
 * guardar, buscar, listar y eliminar funcionen correctamente.
 */
public class LibroServiceTest {

    private LibroService libroService;

    // --------- SETUP: se ejecuta antes de cada test ----------
    @BeforeEach
    public void setUp() {
        libroService = new LibroService();  // crear una instancia nueva antes de cada prueba
    }

    // --------- TEST 1: Guardar un libro ---------------------
    @Test
    public void testGuardarLibro() {
        Libro libro = new Libro();
        libro.setTitulo("Cien Años de Soledad");
        libro.setAutor("Gabriel García Márquez");

        Libro guardado = libroService.guardarLibro(libro);

        assertNotNull(guardado.getId(), "El ID no debería ser nulo");
        assertEquals("Cien Años de Soledad", guardado.getTitulo());
        assertEquals("Gabriel García Márquez", guardado.getAutor());
    }

    // --------- TEST 2: Buscar un libro por ID ----------------
    @Test
    public void testBuscarLibroPorId() {
        Libro libro = new Libro();
        libro.setTitulo("El Principito");
        libro.setAutor("Antoine de Saint-Exupéry");

        Libro guardado = libroService.guardarLibro(libro);
        Libro encontrado = libroService.buscarLibroPorId(guardado.getId());

        assertNotNull(encontrado);
        assertEquals("El Principito", encontrado.getTitulo());
    }

    // --------- TEST 3: Listar libros -------------------------
    @Test
    public void testListarLibros() {
        Libro libro1 = new Libro();
        libro1.setTitulo("1984");
        libro1.setAutor("George Orwell");

        Libro libro2 = new Libro();
        libro2.setTitulo("Fahrenheit 451");
        libro2.setAutor("Ray Bradbury");

        libroService.guardarLibro(libro1);
        libroService.guardarLibro(libro2);

        List<Libro> libros = libroService.listarLibros();

        assertEquals(2, libros.size());
    }

    // --------- TEST 4: Eliminar un libro ---------------------
    @Test
    public void testEliminarLibro() {
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");

        Libro guardado = libroService.guardarLibro(libro);
        Long id = guardado.getId();

        libroService.eliminarLibro(id);
        Libro eliminado = libroService.buscarLibroPorId(id);

        assertNull(eliminado, "El libro debería haber sido eliminado");
    }
}
