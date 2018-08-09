package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
@Repository
public interface ParadaRepository extends CrudRepository<Parada, Long> {
    //Eliminar una parada
    void deleteById(Long id);

    //mostrar todas las paradas
    List<Parada> findAllByHabilitadoIsTrue();

    Parada save(Parada parada);


    Parada findParadaByCoordenadaAndHabilitadoIsTrue(Coordenada coordenada);

    //mostrar las paradas de una ruta dada
    List<Parada> findAllByRutaIdAndHabilitadoIsTrue(Long id);

    List<Parada> findParadaByRutaIdAndHabilitadoIsTrue(Long id, Pageable pagin);

    //Eliminar las paradas de una ruta
    void deleteAllByRutaId(Long id);

    //Mostrar una parada
    Parada findByIdAndHabilitadoIsTrue(Long id);

    //modificar una parada
    @Modifying
    @Query("UPDATE Parada p SET p.coordenada = :coordenada, p.nombre=:nombre, p.ruta=:ruta,p.paradaAnterior=:paradaAnterior, p.paradaSiguiente=:paradaSiguiente WHERE p.id=:id")
    void modifyParadaById(@Param("coordenada")Coordenada coordenada, @Param("nombre")String nombre, @Param("ruta") Ruta ruta, @Param("paradaAnterior")Long paradaAnterior, @Param("paradaSiguiente")Long paradaSiguiente, @Param("id")Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from Parada p where p.id = :id")
    void deleteParadaBy(@Param("id")Long id);

}