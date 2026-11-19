package eus.birt.dam.ad05.web;

import eus.birt.dam.ad05.domain.Receta;
import eus.birt.dam.ad05.service.RecetaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    // Inyeccion por constructor
    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    // 1. LISTADO DE RECETAS (GET /recetas)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("recetas", recetaService.findAll());
        // Buscará el HTML en templates/recetas/listar.html
        return "recetas/listar";
    }

    // 2. FORMULARIO DE ALTA (GET /recetas/nueva)
    @GetMapping("/nueva")
    public String mostrarFormularioAlta(Model model) {
        // Pasamos una receta vacía para que Thymeleaf rellene el formulario
        model.addAttribute("receta", new Receta());
        return "recetas/formulario"; 
    }

    // 3. FORMULARIO DE EDICIÓN (GET /recetas/editar/{id})
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Receta receta = recetaService.findById(id);
        if (receta != null) {
            model.addAttribute("receta", receta);
            return "recetas/formulario";
        } else {
            // Si el ID no existe, volvemos al listado
            return "redirect:/recetas";
        }
    }

    // 4. GUARDAR (POST /recetas/guardar) - Sirve para Alta y Edición
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("receta") Receta receta) {
        // Al guardar, si la receta tiene ID, Hibernate actualiza. 
        // Si no tiene ID, Hibernate inserta una nueva.
        recetaService.save(receta);
        // Redirección tras guardar
        return "redirect:/recetas";
    }
    
    // 5. MOSTRAR CONFIRMACIÓN DE BORRADO (GET)
    @GetMapping("/borrar/{id}")
    public String mostrarConfirmacionBorrado(@PathVariable Long id, Model model) {
        Receta receta = recetaService.findById(id);
        if (receta != null) {
            model.addAttribute("receta", receta);
            return "recetas/confirmar-borrado";
        }
        return "redirect:/recetas";
    }

    // 6. EJECUTAR EL BORRADO REAL (POST)
    @PostMapping("/borrar/{id}")
    public String ejecutarBorrado(@PathVariable Long id) {
        recetaService.deleteById(id);
        return "redirect:/recetas";
    }
}