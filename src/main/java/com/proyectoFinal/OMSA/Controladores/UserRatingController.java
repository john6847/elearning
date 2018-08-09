package com.proyectoFinal.OMSA.Controladores;

import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Services.RolServices;
import com.proyectoFinal.OMSA.Services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dany on 06/11/2017.
 */
@Controller
@RequestMapping("/rating")
public class UserRatingController {
    @Autowired
    private UsuarioServices usuarioServices;
    @Autowired
    private RolServices rolServices;
    @RequestMapping("/verComentarios/")
    public String verUserRating(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("username").toString();
        Usuario user = usuarioServices.buscarUsuarioPorUsername(username);
        user.setRoles(rolServices.rolesUsuario(user));
        model.addAttribute("usuario", user);
        return "user_rating";
    }
}
