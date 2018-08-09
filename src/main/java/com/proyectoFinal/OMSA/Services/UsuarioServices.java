package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Service
public class UsuarioServices {
    @Autowired
   private UsuarioRepository usuarioRepository;
    @Autowired
    private RolServices rolServices;
    @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
   private UsuarioServices usuarioServices;

    @Transactional
    public void  eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        return usuarioRepository.findByUsernameAndHabilitadoIsTrue(username);
    }

    public List<Usuario> buscarUsuarios(int page, int itemPerPage){
        Pageable pageable = new PageRequest(page,itemPerPage);
        return usuarioRepository.findAllByHabilitadoIsTrue(pageable);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAllByHabilitadoIsTrue();
    }

    public List<Usuario> buscarUsuarioPorNombreUsuario(String username){
        return usuarioRepository.findAllByHabilitadoIsTrueAndUsername(username);
    }

    @Transactional
    public void eliminarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarUnUsuario(Long id){
        return usuarioRepository.findByIdAndHabilitadoIsTrue(id);
    }

    @Transactional
    public void crearAdmin(){
        List<Usuario> usuarios = usuarioRepository.findAllByHabilitadoIsTrueAndUsername("admin");
        System.out.println(usuarios.size() +"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");

//        System.out.println("usuarios.get(0).getUsername() = " + usuarios.get(0).getUsername());

//        List<Rol> roles = rolServices.rolesUsuario(usuarios.get(0));
//        System.out.println("roles.get(0).getRol() + roles.get(0).getUsuario().getId() = " + roles.get(0).getRol() + roles.get(0).getUsuario().getUsername());
        if(usuarios.size()<1){
            System.out.println("There+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Usuario usuario =  new Usuario();
            usuario.setName("OMSA");
            usuario.setUsername("admin");
            usuario.setPassword(bCryptPasswordEncoder.encode("omsa1234"));
            guardarUsuario(usuario);
            Rol rol = new Rol();
            rol.setUsername(usuario.getUsername());
            rol.setRol("ROLE_ADMIN");
            Rol rol2 = new Rol();
            rol2.setUsername(usuario.getUsername());
            rol2.setRol("ROLE_USER");
            //rolServices.creacionRol(rol);
            List<Rol> rols = new ArrayList<>();
            rols.add(rol);
            rols.add(rol2);
            usuario.setRoles(rols);
            usuarioServices.guardarUsuario(usuario);
        }
    }
}

