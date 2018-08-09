package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
@Repository
public interface CoordenadaRepository extends CrudRepository<Coordenada, Long> {

    List<Coordenada> findAllByHabilitadoIsTrue();

    Coordenada findByIdAndHabilitadoIsTrue(Long id);

    Coordenada save(Coordenada coordenada);

    Coordenada findCoordenadaByLatitudeAndLongitudAndHabilitadoIsTrue(double latitud, double longitud);
    void deleteCoordenadaById(Long id);



}
