package com.libros.reserva_libros.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libros.reserva_libros.model.Ejemplar;
import com.libros.reserva_libros.model.Libro;
import com.libros.reserva_libros.service.EjemplarService;
import com.libros.reserva_libros.service.LibroService;


@Controller
@RequestMapping("/dashboard/ejemplares")
public class DashboardEjemplaresController {

    @Autowired
    private EjemplarService ejemplarService;

    @Autowired
    private LibroService libroService;

    @GetMapping
    public String mostrarEjemplares(Model model) {
        model.addAttribute("ejemplares", ejemplarService.listarEjemplares());
        model.addAttribute("libros", libroService.listarLibros());
        model.addAttribute("ejemplar", new Ejemplar());
        return "ejemplares";
    }

    @PostMapping("/guardar")
    public String guardarEjemplar(@ModelAttribute("ejemplar") Ejemplar ejemplar) {
        ejemplarService.guardarEjemplar(ejemplar);
        return "redirect:/dashboard/ejemplares";
    }

    @GetMapping("/editar/{id}")
    public String editarEjemplar(@PathVariable Long id,Model model) {
        model.addAttribute("estados",ejemplarService.getEstados());
        model.addAttribute("libros", libroService.listarLibros());
        model.addAttribute("ejemplar",ejemplarService.buscarEjemplarporId(id));
        return "editar-ejemplar";
    }

    @PostMapping("/actualizar")
    public String actualizarEjemplar(@ModelAttribute Ejemplar ejemplar) {
        ejemplarService.guardarEjemplar(ejemplar);
        return "redirect:/dashboard/ejemplares";
    }
        

    @GetMapping("/eliminar/{id}")
    public String eliminarEjemplar(@PathVariable Long id) {
        ejemplarService.eliminarEjemplar(id);
        return "redirect:/dashboard/ejemplares";
    }
    

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Libro.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String id) throws IllegalArgumentException {
                try {
                    Long libroId = Long.valueOf(id);
                    Libro libro = libroService.buscarLibroPorId(libroId);
                    setValue(libro);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

}
