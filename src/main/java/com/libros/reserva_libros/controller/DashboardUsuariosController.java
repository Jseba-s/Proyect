package com.libros.reserva_libros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/usuarios")
public class DashboardUsuariosController{
    @GetMapping
    public String mostrarUsuarios() {
        return "usuarios";
    }
    
}
