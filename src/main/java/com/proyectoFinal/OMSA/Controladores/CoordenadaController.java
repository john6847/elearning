package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Coordenada;
import com.proyectoFinal.OMSA.Entities.Ruta;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.CoordenadaServices;
import com.proyectoFinal.OMSA.Services.RolServices;
import com.proyectoFinal.OMSA.Services.RutaServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * Created by anyderre on 31/07/17.
 */
@Controller
@RequestMapping("/coordenada")
public class CoordenadaController {
   @Autowired
    private RutaServices rutaServices;
   @Autowired
    private CoordenadaServices coordenadaServices;

   @Autowired
   private UsuarioServices usuarioServices;
    @Autowired
    private RolServices rolServices;

    @RequestMapping("/crear/{id}")
    public String crearCoordenada(@PathVariable("id")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @PostMapping("/crear")
    public String guardarCoordenadaCreada(@RequestParam("latitude")double latitud, @RequestParam("longitud")double longitud, @RequestParam("ruta")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        Ruta currentRuta = rutaServices.buscarRutaPorId(id);
        //List<Ruta> rutas = rutaServices.buscarRutaPorNombreCorredor(currentRuta.getNombreCorredor());

        boolean guardado = false;
        Coordenada coordenada = new Coordenada();
        coordenada.setLatitude(latitud);
        coordenada.setLongitud(longitud);

        currentRuta.getCoordenadas().add(coordenada);
        if(rutaServices.guardarRuta(currentRuta)!=null){
            guardado= true;
        }
        if(guardado){
            model.addAttribute("message", "success");
        }else {
            model.addAttribute("message", "error");
        }
        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id));
        model.addAttribute("coordenada", new Coordenada());
        return "crear_coordenada";
    }

    @RequestMapping("/editar/{id_ruta}/{id_coordenada}")
    public String editarCoordenada( @PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada") Long id_coordenada,  HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
        Coordenada coordenada = coordenadaServices.buscarUnaCoordenada(id_coordenada);
        model.addAttribute("id_coordenada",String.valueOf(coordenada.getId()));
        model.addAttribute("longitud",String.valueOf(coordenada.getLongitud()));
        model.addAttribute("latitude",String.valueOf(coordenada.getLatitude()));
        return "editar_coordenada";
    }

    @PostMapping("/editar")
    public ModelAndView guardarCoordenadaModificada(@RequestParam("latitude")double latitude,@RequestParam("longitud")double longitud,
                                                    @RequestParam("ruta")Long id_ruta, @RequestParam("coordenada")Long id_coordenada, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        Ruta currentRuta = rutaServices.buscarRutaPorId(id_ruta);
        boolean modificado = false;
        Coordenada coordenada = coordenadaServices.buscarUnaCoordenada(id_coordenada);

        coordenada.setLatitude(latitude);
        coordenada.setLongitud(longitud);
        if(coordenadaServices.guardarCoordenada(coordenada)!=null){
            modificado= true;
        }

        if(modificado){
            return new ModelAndView("redirect:/ruta/listar/coordenadas/"+currentRuta.getId());
        }else {
            model.addAttribute("ruta", rutaServices.buscarRutaPorId(id_ruta));
            model.addAttribute("id_coordenada",String.valueOf(coordenada.getId()));
            model.addAttribute("longitud",String.valueOf(coordenada.getLongitud()));
            model.addAttribute("latitude",String.valueOf(coordenada.getLatitude()));
            return new ModelAndView("crear_coordenada");
        }

    }

    @Transactional
    @RequestMapping("/eliminar/{id_ruta}/{id_coordenada}")
    public ModelAndView eliminarCoordenada(@PathVariable("id_ruta")Long id_ruta, @PathVariable("id_coordenada")Long id_coordenada ){
//        List<Coordenada> coordenadas= rutaServices.buscarRutaPorId(id_ruta).getCoordenadas();
//        for(Coordenada coordenada:coordenadas) {
//            if (coordenada.getId().equals(id_coordenada)) {
//                coordenadas.remove(coordenada);
//            }
//        }
//        Ruta ruta =  rutaServices.buscarRutaPorId(id_ruta);
//        ruta.setCoordenadas(coordenadas);
//        rutaServices.guardarRuta(ruta);
        Coordenada coordenada = coordenadaServices.buscarUnaCoordenada(id_coordenada);
        coordenada.setHabilitado(false);
        return new ModelAndView("redirect:/ruta/listar/coordenadas/"+id_ruta);
    }

}
