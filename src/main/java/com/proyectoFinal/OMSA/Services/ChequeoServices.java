package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class ChequeoServices {
    @Autowired
    ChequeoRepository chequeoRepository;

    @Transactional
    public Chequeo guardarChequeo(Chequeo chequeo){
        return chequeoRepository.save(chequeo);
    }

    public List<Chequeo> buscarChequeoPorFechaRegistradaYPorTipo(Chequeo chequeo){
        return chequeoRepository.findAllByEsEntradaAndFechaRegistrada(chequeo.getEsEntrada(), chequeo.getFechaRegistrada());
    }

    public List<Chequeo> buscarChequeoEntreRangoFecha(Long fecha1, Long fecha2){
        return chequeoRepository.findAllByFechaRegistradaBetween(fecha1, fecha2);
    }

    public List<Chequeo> buscarChequeoDespuesDeUnaFecha(Long fecha){
        return chequeoRepository.findAllByFechaRegistradaGreaterThan(fecha);
    }

    public List<Chequeo> buscarChequeAntesDeUnaFecha(Long fecha){
        return  chequeoRepository.findAllByFechaRegistradaLessThan(fecha);
    }

    public List<Chequeo> buscarChequoPorParadaId(Long id){
        return chequeoRepository.findChequeoByParadaIdOrderByFechaRegistrada(id);
    }
    public List<Chequeo> buscarChequeoPorAutobusId(Long id){
        return chequeoRepository.findAllByAutobusId(id);
    }
    public List<Chequeo> buscarChequeoPorParadaIdAndCaracteristicas(Chequeo chequeo){
        return chequeoRepository.findChequeoByParadaIdAndEsEntradaAndFechaRegistrada(chequeo.getParada().getId(), chequeo.getEsEntrada(), chequeo.getFechaRegistrada());
    }
    @Transactional
    public void eliminarChequeoPorFecha(Long fecha){
        chequeoRepository.deleteChequeoByFechaRegistrada(fecha);
    }

    @Transactional
    public void eliminarChequeoPorRangoDeFecha(Long fecha1, Long fecha2){
         chequeoRepository.deleteChequeoByFechaRegistradaBetween(fecha1, fecha2);
    }

    @Transactional
    public void eliminarChequeoDespuesDeUnaFecha(Long fecha){
         chequeoRepository.deleteChequeoByFechaRegistradaGreaterThan(fecha);
    }


    public List<Object[]> movimientoMensual(){
        return chequeoRepository.selectMoviemientoMensual();
    }

    public List<Object[]> selectMovimientoAnual(){
        return chequeoRepository.selectMovimientoAnual();
    }

    public List<Object[]> selectGananciaAyer(){
        return chequeoRepository.selectGananciaAyer();
    }
    public List<Object[]> selectGananciaMensual(){
        return chequeoRepository.selectGananciaMensual();
    }
    public List<Object[]> selectGananciaUltimoMes(){
        return chequeoRepository.selectGananciaUltimoMes();
    }

    public List<Object[]> selectMovimientoPorRuta(){
        return chequeoRepository.selectMoviementoPorRuta();
    }
    public List<Object[]> selectGananciaUltimaSemana(){
        return chequeoRepository.selectGananciaUltimaSemana();
    }

    public List<Object[]> selectMovimientoPorRutaAnual(){
        return chequeoRepository.selectMoviementoPorRutaAnual();
    }

    public List<Object[]> selectMovimientoPorRutaUltimos20Minutos(){
        return chequeoRepository.selectMoviementoPorRutaUltimos20minutos();
    }
}

