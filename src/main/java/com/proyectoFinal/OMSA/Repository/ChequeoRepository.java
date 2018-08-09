package com.proyectoFinal.OMSA.Repository;

import com.proyectoFinal.OMSA.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by anyderre on 30/06/17.
 */
@Repository
public interface ChequeoRepository extends CrudRepository<Chequeo, BigInteger> {

    Chequeo save (Chequeo chequeo);

    //List<Chequeo> findAll();

    List<Chequeo> findAllByEsEntradaAndFechaRegistrada(Boolean esEntrada, Long fechaRegistrada);

    List<Chequeo> findAllByAutobusId(Long id);

    List<Chequeo> findAllByFechaRegistradaBetween(Long fecha1, Long fecha2);

    List<Chequeo> findAllByFechaRegistradaGreaterThan(Long fechaRegistrada);

    List<Chequeo> findAllByFechaRegistradaLessThan(Long fechaRegistrada);

    //Actividad en una parada
    List<Chequeo> findChequeoByParadaIdOrderByFechaRegistrada(Long id);


    List<Chequeo> findChequeoByParadaIdAndEsEntradaAndFechaRegistrada(Long id, Boolean esEntrada, Long fechaRegistrada);


    void deleteChequeoByFechaRegistrada(Long fechaRegistrada);

    void deleteChequeoByFechaRegistradaGreaterThan(Long fechaRegistrada);

    void deleteChequeoByFechaRegistradaBetween(Long fecha1, Long fecha2);

    String q1 = "SELECT (to_char(date_trunc('month', date(to_timestamp(fecha_registrada))), 'Mon-YYYY')) AS Mes, count(c.id) AS Cantidad FROM chequeo c WHERE c.es_entrada = true AND date(to_timestamp(c.fecha_registrada))>=now() - interval '1 year'  GROUP BY Mes ORDER BY Mes";
    String q2 = "SELECT (to_char(date_trunc('year', date(to_timestamp(fecha_registrada))), 'YYYY'))AS Ano, count(c.id) AS Cantidad FROM chequeo c WHERE to_timestamp(c.fecha_registrada)>=current_date - interval '10 year' and c.es_entrada = true GROUP BY Ano ORDER BY Ano;";
    String q3 = "select count(c.id) as cantidad, sum(a.precio) as precio from chequeo c  inner join autobus a  on a.id = c.autobus_id where c.es_entrada=true and  to_timestamp(c.fecha_registrada) >=  date(date_trunc('day', CURRENT_DATE - interval '2 day')) and date( to_timestamp(c.fecha_registrada)) <  date(date_trunc('day', CURRENT_DATE));";
    String q4 = "SELECT (to_char(date_trunc('month', date(to_timestamp(fecha_registrada))), 'MM-YYYY')) AS Mes , sum(a.precio) as " +
                "Ganancia FROM chequeo c  inner join autobus a  on a.id = c.autobus_id WHERE date( to_timestamp(c.fecha_registrada))>date_trunc('month', CURRENT_DATE - interval '1 year') and c.es_entrada = true GROUP BY Mes ORDER BY Mes;";
    String q5 = "SELECT (to_char(date(to_timestamp(c.fecha_registrada)),'Mon-dd')) as dia, sum(a.precio) AS precio from chequeo c  inner join autobus a  on a.id = c.autobus_id where c.es_entrada=true and to_timestamp(c.fecha_registrada)>= date_trunc('month', current_date - interval '1' month) and to_timestamp(c.fecha_registrada)< date_trunc('month', current_date) group by dia order by dia;";
    String q6 = "SELECT count(c.id) as cantidad, r.nombre_corredor as ruta FROM chequeo c  inner join ruta r  on r.id = c.id_ruta WHERE date(to_timestamp(c.fecha_registrada))>= date_trunc('month', current_date - interval '1' month) and date(to_timestamp(c.fecha_registrada))< date_trunc('month', current_date) and c.es_entrada = true GROUP BY ruta order by ruta";
    String q7= "select count(c.id) as cantidad, sum(a.precio) as precio from chequeo c  inner join autobus a  on a.id = c.autobus_id where c.es_entrada=true and  to_timestamp(c.fecha_registrada) >=  date(date_trunc('week', CURRENT_DATE - interval '1 week')) and date( to_timestamp(c.fecha_registrada)) <  date(date_trunc('week', CURRENT_DATE));";
    String q8 = "SELECT count(c.id) as cantidad, r.nombre_corredor as ruta\n" +
            "FROM chequeo c  inner join ruta r  on r.id = c.id_ruta\n" +
            "WHERE date(to_timestamp(c.fecha_registrada)) >= date_trunc('month', current_date) and c.es_entrada = true \n" +
            "GROUP BY ruta\n" +
            "order by ruta ";

    String q9="SELECT count(c.id) as cantidad, r.nombre_corredor as ruta FROM chequeo c  inner join ruta r  on r.id = c.id_ruta WHERE to_timestamp(c.fecha_registrada) >= current_timestamp - interval '20 minutes' and c.es_entrada = true \n" +
            "GROUP BY ruta\n" +
            "order by ruta ";

    // movimiento mensual
    @Query(value =q1, nativeQuery = true)
    List<Object[]> selectMoviemientoMensual();

   // movimiento anual de autobus
   @Query( value=q2, nativeQuery = true)
    List<Object[]> selectMovimientoAnual();

    // Ganancia por dia de ayer
    @Query( value=q3, nativeQuery = true)
    List<Object[]> selectGananciaAyer();
    // Ganancia Ultima semana
    @Query( value=q7, nativeQuery = true)
    List<Object[]> selectGananciaUltimaSemana();
   // Ganancia Mensual
    @Query(value = q4, nativeQuery = true)
    List<Object[]> selectGananciaMensual();

    // Ganancia ultimo mes por dia
    @Query(value =q5, nativeQuery = true)
    List<Object[]> selectGananciaUltimoMes();

    //Ruta con mas Actividad
    @Query(value = q6, nativeQuery = true)
    List<Object[]> selectMoviementoPorRuta();

    //Movimiento este mes
    @Query(value = q8, nativeQuery = true)
    List<Object[]> selectMoviementoPorRutaAnual();

    //Actividad Ultimos 20 minutos
    @Query(value = q9, nativeQuery = true)
    List<Object[]> selectMoviementoPorRutaUltimos20minutos();

}
