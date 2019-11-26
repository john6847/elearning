package com.sorbSoft.CabAcademie.Services;

import com.sorbSoft.CabAcademie.Entities.Rol;
import com.sorbSoft.CabAcademie.Entities.Usuario;
import com.sorbSoft.CabAcademie.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by Dany on 22/04/2019.
 */

@Service
public class SecurityServices implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;



    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * Creando el usuario por defecto y su rol.
     */
    public void crearAdmin() {
        Usuario userAdmin = userRepository.findByUsername("bradmin");
        if (userAdmin == null) {

            Rol rolSuperMegaAdmin = new Rol();
            rolSuperMegaAdmin.setRol("ROLE_SUPER_MEGA_ADMIN");
            List<Rol> roles = new ArrayList<>();
            roles.add(rolSuperMegaAdmin);
            Usuario admin = new Usuario();
            admin.setRoles(roles);
            admin.setUsername("bradmin");
            admin.setName("BR-Administrator");
            admin.setPassword(bCryptPasswordEncoder.encode("brtenant")); // Todo: Change password
//            admin.setCreationDate(new Date());
//            admin.setModificationDate(new Date());
            userRepository.save(admin);

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty()) {
            throw new UsernameNotFoundException("Username must be provided");
        }

        Usuario usuario = userRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(
                    String.format("Ce nom d'utilisateur n'existe pas antrepriz=%s",
                            username));
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getRol()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), usuario.getEnable() == 1, true, true, true, grantedAuthorities);
    }
}