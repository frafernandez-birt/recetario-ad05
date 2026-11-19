package eus.birt.dam.ad05.web;

import eus.birt.dam.ad05.domain.Paso;
import eus.birt.dam.ad05.domain.Receta;
import eus.birt.dam.ad05.service.PasoService;
import eus.birt.dam.ad05.service.RecetaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pasos")
public class PasoController {

    private final PasoService pasoService;
    private final RecetaService recetaService;

    // Inyectamos los dos servicios
    public PasoController(PasoService pasoService, RecetaService recetaService) {
        this.pasoService = pasoService;
        this.recetaService = recetaService;
    }

    // 1. LISTADO DE PASOS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pasos", pasoService.findAll());
        return "pasos/listar";
    }

    // 2. FORMULARIO DE ALTA
    @GetMapping("/nuevo")
    public String mostrarFormularioAlta(Model model) {
        List<Receta> recetas = recetaService.findAll();
        
        // Comprobación: si no hay recetas, avisamos y redirigimos
        if (recetas.isEmpty()) {
            // Nota: Usamos un truco simple de redirección, sin RedirectAttributes
            return "redirect:/pasos?error=sin_recetas";
        }

        model.addAttribute("paso", new Paso());
        model.addAttribute("recetas", recetas); // Enviamos la lista para el selector
        return "pasos/formulario";
    }

    // 3. FORMULARIO DE EDICIÓN
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Paso paso = pasoService.findById(id);
        if (paso != null) {
            model.addAttribute("paso", paso);
            model.addAttribute("recetas", recetaService.findAll());
            return "pasos/formulario";
        } else {
            return "redirect:/pasos";
        }
    }

    // 4. GUARDAR (Alta y Edición)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("paso") Paso paso) {
        // Comprobamos que la receta seleccionada realmente existe (seguridad)
        if (paso.getReceta() != null && paso.getReceta().getId() != null) {
            Receta recetaReal = recetaService.findById(paso.getReceta().getId());
            if (recetaReal != null) {
                paso.setReceta(recetaReal); // Le asignamos la receta completa
                pasoService.save(paso);
            }
        }
        return "redirect:/pasos";
    }
    
    // 5. MOSTRAR CONFIRMACIÓN DE BORRADO (GET)
    @GetMapping("/borrar/{id}")
    public String mostrarConfirmacionBorrado(@PathVariable Long id, Model model) {
        Paso paso = pasoService.findById(id);
        if (paso != null) {
            model.addAttribute("paso", paso);
            return "pasos/confirmar-borrado";
        }
        return "redirect:/pasos";
    }

    // 6. EJECUTAR EL BORRADO REAL (POST)
    @PostMapping("/borrar/{id}")
    public String ejecutarBorrado(@PathVariable Long id) {
        pasoService.deleteById(id);
        return "redirect:/pasos";
    }
}