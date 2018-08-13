package com.sorbSoft.CabAcademie.Repository;

import com.sorbSoft.CabAcademie.Entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anyderre on 11/08/17.
 */
@Repository

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario save(Usuario usuario);

    Usuario findByUsername(String username);

    Usuario findById(Long id);

    List<Usuario>findAll(Pageable pageable);

    List<Usuario> findAllByUsername(String username);

    List<Usuario>findAll();

    void deleteById(Long id);
}
