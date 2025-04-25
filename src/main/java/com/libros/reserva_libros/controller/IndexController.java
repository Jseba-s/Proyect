package com.libros.reserva_libros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.libros.reserva_libros.model.Ejemplar;
import com.libros.reserva_libros.service.EjemplarService;

@Controller
public class IndexController {

    @Autowired
    private EjemplarService ejemplarService;

    @GetMapping("/")
    public String showIndex() {
        return "index"; //carga index.html
    }

    // Muestra index.html
    @GetMapping("/misreservas")
    public String showIndexop() {
        return "MisReservas"; //carga reservas.html
    }

    @GetMapping("/dashboard")
    public String verDashboard(Model model) {
        List<Ejemplar> ejemplares = ejemplarService.listarEjemplares();
        model.addAttribute("ejemplaresDisponibles", ejemplarService.listarEjemplaresDisponibles(ejemplares));
        return "dashboard";
    }

    @GetMapping("/eventos")
    public String mostrarEventos() {
        return "eventos";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo() {
        return "catalogo";
    }

    @GetMapping("/tucuenta")
    public String showTucuenta() {
        return "tucuenta";
    }
}
