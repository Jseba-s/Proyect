package com.libros.reserva_libros.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.libros.reserva_libros.model.Ejemplar;

@Service
public class EjemplarService {
    private final Map<Long,Ejemplar> ejemplares=new HashMap<>();
    private final AtomicLong contador=new AtomicLong();
    private final List<String> estados = List.of("Disponible","Reservado", "Prestado","En Reparación");

    public List<Ejemplar> listarEjemplares(){
        return new ArrayList<>(ejemplares.values());
    }

    public Ejemplar guardarEjemplar(Ejemplar ejemplar){
        if(ejemplar.getId()==null){
            Long id=contador.incrementAndGet();
            ejemplar.setId(id);
        }
        ejemplares.put(ejemplar.getId(),ejemplar);
        return ejemplar;
    }

    public Ejemplar buscarEjemplarporId(Long id){
        return ejemplares.get(id);
    }

    public void eliminarEjemplar(Long id){
        ejemplares.remove(id);
    }

    public List<Ejemplar> listarEjemplaresDisponibles(List<Ejemplar> ejemplares){
        return ejemplares.stream()
            .filter(e -> "Disponible".equalsIgnoreCase(e.getEstado()))
            .collect(Collectors.toList());
    }

    public List<String> getEstados() {
        return estados;
    }
}
