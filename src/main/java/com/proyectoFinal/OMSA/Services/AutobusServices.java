package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class AutobusServices {
    @Autowired
    AutobusRepository autobusRepository;

    public List<Autobus> buscarAutobusPorRutaId(Long id, int page, int itemsPerPage){
        Pageable pageable = new PageRequest(page, itemsPerPage);
        return autobusRepository.findAutobusesByHabilitadoIsTrueAndRutaId(id,pageable);
    }
    public List<Autobus> buscarAutobusPorRutaNull(int page, int itemsPerPage){
        Pageable pageable = new PageRequest(page, itemsPerPage);
        return autobusRepository.findAutobusesByHabilitadoIsTrueAndRutaIsNull(pageable);
    }
    public int buscarAutobusesNull(){
        return autobusRepository.findAutobusesByHabilitadoIsTrueAndRutaIsNull().size();
    }
    public List<Autobus> buscarTodoLosAutobus(){
        return autobusRepository.findAllByHabilitadoIsTrue();
    }

    public List<Autobus> buscarTodosLosAutobusporRuta(Long id){
        return autobusRepository.findAutobusesByHabilitadoIsTrueAndRutaId(id);
    }


    public Autobus guardarAutobus(Autobus autobus){
        return autobusRepository.save(autobus);
    }

    @Transactional
    public void modificarAutobus(){
        ArrayList<Autobus> autobuses = autobusRepository.findAllByHabilitadoIsTrueAndActivoIsTrue();
        System.out.println(autobuses.size());
        for(Autobus autobus: autobuses){
            autobus.setUltimaFechaModificada(new Date().getTime());
            autobus.setActivo(false);
            autobus.setUltimaParada(null);
            autobus.setCantidadDePasajerosActual(0);
            autobus.setRuta(null);
            autobusRepository.save(autobus);
        }

    }

    public Autobus buscarAutobusPorId(Long id){
        return autobusRepository.findAutobusByIdAndHabilitadoIsTrue(id);
    }

    @Transactional
    public void eliminarAutobusporId(Long id){
        autobusRepository.deleteAutobusById(id);
    }

    @Transactional
    public void modificarEstadoAutobus(Autobus autobus){
         autobusRepository.modifyEstadoAutobusById(autobus.getActivo(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public void modifcarCoordenadaAutobus(Autobus autobus){
         autobusRepository.modifyCoordenadaAutobusById(autobus.getCoordenada(), autobus.getUltimaFechaModificada(), autobus.getId());
    }

    @Transactional
    public void modificarCantidadPasajeros(Autobus autobus){
        autobusRepository.modifyCantidadPasajerosActualDelAutobusById(autobus.getCantidadDePasajerosActual(), autobus.getUltimaParada()
        ,autobus.getUltimaFechaModificada(), autobus.getId());
    }

    public Autobus buscarUnAutobus(Long id){
        return autobusRepository.findAutobusByIdAndHabilitadoIsTrue(id);
    }

    public Autobus buscarAutobusPorRaspberryNumeroSerial(String numeroSerial){
        return autobusRepository.findAutobusByHabilitadoIsTrueAndRaspberryPiNumeroSerial(numeroSerial);
    }

    public List<Autobus> buscarAutobusActivosYPorRuta(Boolean activo, Ruta ruta){
        return  autobusRepository.findAllByHabilitadoIsTrueAndActivoAndRuta(activo, ruta);
    }

    public List<Autobus> buscarAutobusPorUltimaParadaID(Long id){
        return buscarAutobusPorUltimaParadaID(id);
    }

    @Transactional
    public void modificarRutaAutobus(Autobus autobus){
         autobusRepository.modifyRutaActualAutobus(autobus.getRuta(),autobus.getId());
    }

    public Integer contarAutobusInactivo(){
        return autobusRepository.countByActivoFalse();
    }

}
