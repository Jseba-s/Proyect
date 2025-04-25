package com.libros.reserva_libros.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.libros.reserva_libros.model.Ejemplar;

/**
 * Pruebas unitarias para EjemplarService
 */
public class EjemplarServiceTest {

    private EjemplarService ejemplarService;

    // -------- SETUP: Antes de cada prueba --------
    @BeforeEach
    public void setUp() {
        ejemplarService = new EjemplarService();
    }

    // -------- TEST 1: Guardar ejemplar ----------
    @Test
    public void testGuardarEjemplar() {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setCodigoEjemplar("EJ123");
        ejemplar.setEstado("Disponible");

        Ejemplar guardado = ejemplarService.guardarEjemplar(ejemplar);

        assertNotNull(guardado.getId());
        assertEquals("EJ123", guardado.getCodigoEjemplar());
        assertEquals("Disponible", guardado.getEstado());
    }

    // -------- TEST 2: Buscar por ID -------------
    @Test
    public void testBuscarEjemplarPorId() {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setCodigoEjemplar("EJ456");

        Ejemplar guardado = ejemplarService.guardarEjemplar(ejemplar);
        Ejemplar encontrado = ejemplarService.buscarEjemplarporId(guardado.getId());

        assertNotNull(encontrado);
        assertEquals("EJ456", encontrado.getCodigoEjemplar());
    }

    // -------- TEST 3: Listar todos --------------
    @Test
    public void testListarEjemplares() {
        ejemplarService.guardarEjemplar(new Ejemplar());
        ejemplarService.guardarEjemplar(new Ejemplar());

        List<Ejemplar> lista = ejemplarService.listarEjemplares();
        assertEquals(2, lista.size());
    }

    // -------- TEST 4: Eliminar ejemplar ---------
    @Test
    public void testEliminarEjemplar() {
        Ejemplar ejemplar = new Ejemplar();
        ejemplar.setCodigoEjemplar("EJ789");

        Ejemplar guardado = ejemplarService.guardarEjemplar(ejemplar);
        Long id = guardado.getId();

        ejemplarService.eliminarEjemplar(id);
        Ejemplar eliminado = ejemplarService.buscarEjemplarporId(id);

        assertNull(eliminado);
    }

    // -------- TEST 5: Filtrar disponibles -------
    @Test
    public void testListarEjemplaresDisponibles() {
        Ejemplar ej1 = new Ejemplar();
        ej1.setEstado("Disponible");

        Ejemplar ej2 = new Ejemplar();
        ej2.setEstado("Reservado");

        Ejemplar ej3 = new Ejemplar();
        ej3.setEstado("Disponible");

        List<Ejemplar> filtrados = ejemplarService.listarEjemplaresDisponibles(List.of(ej1, ej2, ej3));

        assertEquals(2, filtrados.size());
        assertTrue(filtrados.stream().allMatch(e -> "Disponible".equalsIgnoreCase(e.getEstado())));
    }

    // -------- TEST 6: Obtener estados ----------
    @Test
    public void testGetEstados() {
        List<String> estados = ejemplarService.getEstados();
        assertEquals(4, estados.size());
        assertTrue(estados.contains("Disponible"));
        assertTrue(estados.contains("Prestado"));
    }
}

