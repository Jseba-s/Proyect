package com.libros.reserva_libros.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.libros.reserva_libros.model.Libro;

@Service
public class LibroService {
    private final Map<Long,Libro> libros=new HashMap<>();
    private final AtomicLong contador=new AtomicLong();
    

    public List<Libro> listarLibros(){
        return new ArrayList<>(libros.values());
    }

    public Libro guardarLibro(Libro libro){
        if (libro.getId() == null) {
            Long id = contador.incrementAndGet();
            libro.setId(id);
        }
        libros.put(libro.getId(), libro);
        return libro;
    }

    public Libro buscarLibroPorId(Long id){
        return libros.get(id);
    }

    public void eliminarLibro(Long id){
        libros.remove(id);
    }
}
