package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.Rol;
import com.proyectoFinal.OMSA.Entities.Usuario;
import com.proyectoFinal.OMSA.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dany on 07/09/2017.
 */
@Service
public class RolServices {
    //Inyectando el repositorio
    @Autowired
    private RolRepository rolRepository;

    public long cantidadUsuario(){
        return rolRepository.count();
    }

    @Transactional
    public Rol creacionRol(Rol rol){
        rolRepository.save(rol);
        return rol;
    }
    @Transactional
    public void eliminarRolPorUsername(String username){
        rolRepository.deleteRolByUsername(username);
    }

    @Transactional
    public void elimarRolPorId(Long id){
        rolRepository.deleteRolById(id);
    }
    public List<Rol> todosRoles(){
        return rolRepository.findAllByHabilitadoIsTrue();
    }
    public List<Rol> rolesUsuario(Usuario usuario){
        return rolRepository.findAllByUsernameAndHabilitadoIsTrue(usuario.getUsername());
    }


}
