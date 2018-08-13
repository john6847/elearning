package com.sorbSoft.CabAcademie.Services;

import com.sorbSoft.CabAcademie.Entities.Rol;
import com.sorbSoft.CabAcademie.Entities.Usuario;
import com.sorbSoft.CabAcademie.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Service
@Transactional
public class UsuarioServices {
    @Autowired
   private UsuarioRepository usuarioRepository;
    @Autowired
    private RolServices rolServices;
    @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
   private UsuarioServices usuarioServices;

    public void  eliminarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> buscarUsuarios(int page, int itemPerPage){
        Pageable pageable = new PageRequest(page,itemPerPage);
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> buscarTodosUsuarios(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> buscarUsuarioPorNombreUsuario(String username){
        return usuarioRepository.findAllByUsername(username);
    }

    public void eliminarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }
    public Usuario updateUsuario(Usuario user){
        Usuario currentUser = usuarioRepository.findById(user.getId());
        currentUser.setEnable(user.getEnable());
        currentUser.setName(user.getName());
        currentUser.setUsername(user.getUsername());
        return usuarioRepository.save(currentUser);

    }

    public Usuario buscarUnUsuario(Long id){
        return usuarioRepository.findById(id);
    }

    public void crearAdmin(){
        List<Usuario> usuarios = usuarioRepository.findAllByUsername("admin");
        System.out.println(usuarios.size() +"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
        usuarios.get(0).getRoles().forEach(rol ->  System.out.println(rol.getRol() +"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++="));

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

