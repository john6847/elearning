package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.RolServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Controller
@RequestMapping("/zonaAdmin")
public class AdminController {

    @Autowired
    private RolServices rolServices;
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping("/actividad")
    public  String verActividades(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        usuario.setRoles(rolServices.rolesUsuario(usuario));
        model.addAttribute("usuario", usuario);
        model.addAttribute("user",new Usuario());
        return "index";
    }

    @RequestMapping("/usuarios")
    public String index(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        usuario.setRoles(rolServices.rolesUsuario(usuario));
        model.addAttribute("usuario", usuario);
        model.addAttribute("size", usuarioServices.buscarTodosUsuarios().size());
        return "ver_usuarios";
    }

    @RequestMapping("/registrar")
    public  String agregar(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario usuario = usuarioServices.buscarUsuarioPorUsername(username);
        usuario.setRoles(rolServices.rolesUsuario(usuario));
        model.addAttribute("usuario", usuario);
        model.addAttribute("user",new Usuario());
        return "crear_usuarios";
    }
    @PostMapping("/registrar")
    public String agregar(@ModelAttribute Usuario usuario, @RequestParam("theRoles")String[] roles, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        ///Creacion rol
        System.out.println(usuario.getName()+"/"+usuario.getPassword()+"/"+usuario.getUsername());
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        List<Rol> rols = new ArrayList<>();
        if(roles!=null){
            for(String rol : roles){
                Rol r = new Rol();
                r.setRol(rol);
                r.setUsername(usuario.getUsername());
                rols.add(r);
              // rolServices.creacionRol(r);
             //  user.getRoles().add(r);
            }
        }
        usuario.setRoles(rols);
        if (usuarioServices.guardarUsuario(usuario)!=null){
            return "redirect:/zonaAdmin/usuarios";
        }
        model.addAttribute("error", "no se pudo guardar el usuario");
        return "crear_usuarios";
    }

    @RequestMapping("/editar/{id}")
    public  String modificar( @PathVariable("id")Long id, HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);
        model.addAttribute("user", usuarioServices.buscarUnUsuario(id));
        return "editar_usuario";
    }

    @PostMapping("/editar")
    public ModelAndView modificar(@RequestParam("theRoles") String[] roles, @RequestParam("id")Long id, @RequestParam("username") String username,
                                   @RequestParam("name") String nombre, HttpServletRequest request, Model model){
        String uname = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(uname);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);

        Usuario usuario = usuarioServices.buscarUnUsuario(id);
        List<Rol> rols= new ArrayList<>();
        usuario.setRoles(rols);


        usuario.setName(nombre);
        usuario.setUsername(username);
        usuarioServices.guardarUsuario(usuario);
        for (String rol :roles){
            usuario.getRoles().add(new Rol(rol, usuario.getUsername()));
        }

        if (usuarioServices.guardarUsuario(usuario)!=null){
            return new ModelAndView( "redirect:/zonaAdmin/usuarios");
        }


        model.addAttribute("error", "Averigue bien los campos!");
        return new ModelAndView("editar_usuario");
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("/eliminar/usuario/{id}")
    public String eliminarUsuario(@PathVariable("id")Long id){
        Usuario usuario = usuarioServices.buscarUnUsuario(id);
        List<Rol>roles = rolServices.rolesUsuario(usuario);
        for(Rol rol: roles){
            rol.setHabilitado(false);
            rolServices.creacionRol(rol);
        }
        usuario.setHabilitado(false);
        usuarioServices.guardarUsuario(usuario);
        return "redirect:/zonaAdmin/usuarios";
    }

}
