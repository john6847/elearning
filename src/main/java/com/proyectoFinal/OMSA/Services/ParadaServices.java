package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.hibernate.loader.collection.PaddedBatchingCollectionInitializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class ParadaServices {
    @Autowired
    ParadaRepository paradaRepository;
    @Autowired
    RutaServices rutaServices;

    @Transactional
    public void eliminarParadaPor(Long id){
         paradaRepository.deleteById(id);
    }

    @Transactional
    public void eliminarParadaPorRutaId(Long id){
        paradaRepository.deleteAllByRutaId(id);
    }
    public  List<Parada> getTopParadas(Long id, int startPosition, int cantToRead){
        Pageable pageable = new PageRequest(startPosition, cantToRead);
        return paradaRepository.findParadaByRutaIdAndHabilitadoIsTrue(id, pageable);
    }
    @Transactional
    public Parada guardarParada(Parada parada){
        return paradaRepository.save(parada);
    }

    public List<Parada> buscarParadaPorRutaId(Long id){
        return paradaRepository.findAllByRutaIdAndHabilitadoIsTrue(id);
    }

    public List<Parada> buscarTodasParadas(){
        return paradaRepository.findAllByHabilitadoIsTrue();
    }

    public Parada buscarParada(Long id){
        return paradaRepository.findByIdAndHabilitadoIsTrue(id);
    }

    @Transactional
    void modificarParadaPorId(Parada parada){
        paradaRepository.modifyParadaById(parada.getCoordenada(),parada.getNombre(),parada.getRuta(),parada.getParadaAnterior(),parada.getParadaSiguiente(),parada.getId());
    }

    public void guardarParadaAlPrincipio(){
        Ruta ruta1 = rutaServices.buscarRutaPorId((long) 1);
        Ruta ruta2 = rutaServices.buscarRutaPorId((long) 2);
       // List<Parada> paradasRuta1 = new ArrayList<>();

        paradaRepository.save(new Parada("COLEGIO EVANGELICO", ruta1, (long)40, (long)2, new Coordenada(19.48231,-70.70784)));
        paradaRepository.save(new Parada("MERCADO LA PLACITA", ruta1, (long)1, (long)3, new Coordenada(19.48063,-70.70489)));
        paradaRepository.save(new Parada("FERR HATUEY", ruta1, (long)2, (long)4, new Coordenada(19.47953,-70.70300)));
        paradaRepository.save(new Parada("INDUVECA", ruta1, (long)3, (long)5, new Coordenada(19.47743, -70.69935)));
        paradaRepository.save(new Parada("LA ESPERAZA", ruta1, (long)4, (long)6, new Coordenada(19.47401,-70.69427)));
        paradaRepository.save(new Parada("IMPALE", ruta1, (long)5, (long)7, new Coordenada(19.47094,-70.69117)));
        paradaRepository.save(new Parada("BARBERIA", ruta1, (long)6, (long)8, new Coordenada(19.46988,-70.68843)));
        paradaRepository.save(new Parada("EDIFICIO UTESA", ruta1, (long)7, (long)9, new Coordenada(19.46737,-70.68704)));
        paradaRepository.save(new Parada("COOP REAL", ruta1, (long)8, (long)10, new Coordenada(19.46431,-70.68663)));
        paradaRepository.save(new Parada("M ABREU", ruta1, (long)9, (long)11, new Coordenada(19.45984,-70.68603)));
        paradaRepository.save(new Parada("HUACALITO", ruta1, (long)10, (long)12, new Coordenada(19.45393,-70.68508)));
        paradaRepository.save(new Parada("IGLESIA BAUTISTA", ruta1, (long)11, (long)13, new Coordenada(19.44548,-70.68797)));
        paradaRepository.save(new Parada("BARRIO LOTERIA", ruta1, (long)12, (long)14, new Coordenada(19.44166,-70.68727)));
        paradaRepository.save(new Parada("APARTAMENTO LA LOTERIA", ruta1, (long)13, (long)15, new Coordenada(19.43919,-70.68845)));
        paradaRepository.save(new Parada("BANCA T SPORT", ruta1, (long)14, (long)16, new Coordenada(19.43493,-70.69110)));
        paradaRepository.save(new Parada("CRUCE PEKIN", ruta1, (long)15, (long)17, new Coordenada(19.42904,-70.69471)));
        paradaRepository.save(new Parada("COOPERATIVA SAN MIGUEL", ruta1, (long)16, (long)18, new Coordenada(19.42482,-70.69729)));
        paradaRepository.save(new Parada("FRANCELL", ruta1, (long)17, (long)19, new Coordenada(19.42227,-70.69899)));
        paradaRepository.save(new Parada("COLEGIO PADRE EMILIANO", ruta1, (long)18, (long)20, new Coordenada(19.41800,-70.70173)));
        paradaRepository.save(new Parada("APARTAMENTO V V", ruta1, (long)19, (long)21, new Coordenada(19.41586,-70.70327)));
        paradaRepository.save(new Parada("CAMIONERO", ruta1, (long)20, (long)22, new Coordenada(19.41453,-70.71280)));
        paradaRepository.save(new Parada("FABRICA FERROTE", ruta1, (long)21, (long)23, new Coordenada(19.41405,-70.72113)));
        paradaRepository.save(new Parada("GAS WENI", ruta1, (long)22, (long)24, new Coordenada(19.41381,-70.72534)));
        paradaRepository.save(new Parada("LA 7 CASA", ruta1, (long)23, (long)25, new Coordenada(19.41801,-70.73295)));
        paradaRepository.save(new Parada("1 BARRANQUITA", ruta1, (long)24, (long)26, new Coordenada(19.42142,-70.73506)));
        paradaRepository.save(new Parada("COMPLEJO A", ruta1, (long)25, (long)27, new Coordenada(19.42999,-70.72639)));
        paradaRepository.save(new Parada("FARMACIA SALON", ruta1, (long)26, (long)28, new Coordenada(19.43297,-70.72489)));
        paradaRepository.save(new Parada("FERRETERIA OLIMPICA", ruta1, (long)27, (long)29, new Coordenada(19.43865,-70.72282)));
        paradaRepository.save(new Parada("LA FUENTE 2", ruta1, (long)28, (long)30, new Coordenada(19.44108,-70.72207)));
        paradaRepository.save(new Parada("CHECO", ruta1, (long)29, (long)31, new Coordenada(19.45248,-70.72425)));
        paradaRepository.save(new Parada("COLMADO ROSAN", ruta1, (long)30, (long)32, new Coordenada(19.45496,-70.72378)));
        paradaRepository.save(new Parada("CANCHA JOYA", ruta1, (long)31, (long)33, new Coordenada(19.45555,-70.71504)));
        paradaRepository.save(new Parada("ENT RAFEY", ruta1, (long)32, (long)34, new Coordenada(19.47318,-70.72018 )));
        paradaRepository.save(new Parada("LOS CHIVOS", ruta1, (long)33, (long)35, new Coordenada(19.48023,-70.72666)));
        paradaRepository.save(new Parada("ELEVADO", ruta1, (long)34, (long)36, new Coordenada(19.48561,-70.73120)));
        paradaRepository.save(new Parada("D POLO B", ruta1, (long)35, (long)37, new Coordenada(19.48649,-70.72858)));
        paradaRepository.save(new Parada("HOSPITAL", ruta1, (long)36, (long)38, new Coordenada(19.48683,-70.72531)));
        paradaRepository.save(new Parada("MARCOS CAMBIO", ruta1, (long)37, (long)39, new Coordenada(19.48714,-70.72202)));
        paradaRepository.save(new Parada("FARMACIA YNA3", ruta1, (long)38, (long)40, new Coordenada(19.48737,-70.71996)));
        paradaRepository.save(new Parada("DELTA COMERCIAL", ruta1, (long)39, (long)1, new Coordenada(19.48930,-70.71825)));

        paradaRepository.save(new Parada("COLEGIO EVANGELICO", ruta2, (long)79, (long)41, new Coordenada(19.48263,-70.70797)));
        paradaRepository.save(new Parada("MERCADO LA PLACITA", ruta2, (long)78, (long)80, new Coordenada(19.48092,-70.70501)));
        paradaRepository.save(new Parada("FERR HATUEY", ruta2, (long)77, (long)79, new Coordenada(19.47981,-70.70305)));
        paradaRepository.save(new Parada("INDUVECA", ruta2, (long)76, (long)78, new Coordenada(19.47771, -70.69944)));
        paradaRepository.save(new Parada("LA ESPERAZA", ruta2, (long)75, (long)77, new Coordenada(19.47430,-70.69432)));
        paradaRepository.save(new Parada("IMPALE", ruta2, (long)74, (long)76, new Coordenada(19.47115,-70.69124)));
        paradaRepository.save(new Parada("BARBERIA", ruta2, (long)73, (long)75, new Coordenada(19.47008,-70.68852)));
        paradaRepository.save(new Parada("EDIFICIO UTESA", ruta2, (long)72, (long)74, new Coordenada(19.46758,-70.68690)));
        paradaRepository.save(new Parada("COOP REAL", ruta2, (long)71, (long)73, new Coordenada(19.46449,-70.68652)));
        paradaRepository.save(new Parada("M ABREU", ruta2, (long)70, (long)72, new Coordenada(19.46004,-70.68589)));
        paradaRepository.save(new Parada("HUACALITO", ruta2, (long)69, (long)71, new Coordenada(19.45414,-70.68495)));
        paradaRepository.save(new Parada("IGLESIA BAUTISTA", ruta2, (long)68, (long)70, new Coordenada(19.44576,-70.68792)));
        paradaRepository.save(new Parada("BARRIO LOTERIA", ruta2, (long)67, (long)69, new Coordenada(19.44187,-70.68712)));
        paradaRepository.save(new Parada("APARTAMENTO LA LOTERIA", ruta2, (long)66, (long)68, new Coordenada(19.43929,-70.68824)));
        paradaRepository.save(new Parada("BANCA T SPORT", ruta2, (long)65, (long)67, new Coordenada(19.43507,-70.69086)));
        paradaRepository.save(new Parada("CRUCE PEKIN", ruta2, (long)64, (long)66, new Coordenada(19.42914,-70.69447)));
        paradaRepository.save(new Parada("COOPERATIVA SAN MIGUEL", ruta2, (long)63, (long)65, new Coordenada(19.42492,-70.69708)));
        paradaRepository.save(new Parada("FRANCELL", ruta2, (long)62, (long)64, new Coordenada(19.42232,-70.69875)));
        paradaRepository.save(new Parada("COLEGIO PADRE EMILIANO", ruta2, (long)61, (long)63, new Coordenada(19.41807,-70.7015)));
        paradaRepository.save(new Parada("APARTAMENTO V V", ruta2, (long)60, (long)62, new Coordenada(19.41585,-70.70308)));
        paradaRepository.save(new Parada("CAMIONERO", ruta2, (long)59, (long)61, new Coordenada(19.41440,-70.71251)));
        paradaRepository.save(new Parada("FABRICA FERROTE", ruta2, (long)58, (long)60, new Coordenada(19.41391,-70.72089)));
        paradaRepository.save(new Parada("GAS WENI", ruta2, (long)57, (long)59, new Coordenada(19.41366,-70.72506)));
        paradaRepository.save(new Parada("LA 7 CASA", ruta2, (long)56, (long)58, new Coordenada(19.41815,-70.73333)));
        paradaRepository.save(new Parada("1 BARRANQUITA", ruta2, (long)55, (long)57, new Coordenada(19.4217,-70.73495)));
        paradaRepository.save(new Parada("COMPLEJO A", ruta2, (long)54, (long)56, new Coordenada(19.42990,-70.72667 )));
        paradaRepository.save(new Parada("FARMACIA SALON", ruta2, (long)53, (long)55, new Coordenada(19.43283,-70.72513)));
        paradaRepository.save(new Parada("FERRETERIA OLIMPICA", ruta2, (long)52, (long)54, new Coordenada(19.43845,-70.72306)));
        paradaRepository.save(new Parada("LA FUENTE 2", ruta2, (long)51, (long)53, new Coordenada(19.44140,-70.72214)));
        paradaRepository.save(new Parada("CHECO", ruta2, (long)50, (long)52, new Coordenada(19.45272,-70.72400)));
        paradaRepository.save(new Parada("COLMADO ROSAN", ruta2, (long)49, (long)51, new Coordenada(19.45506,-70.72355)));
        paradaRepository.save(new Parada("CANCHA JOYA", ruta2, (long)48, (long)50, new Coordenada(19.45570,-70.71521)));
        paradaRepository.save(new Parada("ENT RAFEY", ruta2, (long)47, (long)49, new Coordenada(19.47331,-70.72035 )));
        paradaRepository.save(new Parada("LOS CHIVOS", ruta2, (long)46, (long)48, new Coordenada(19.48036,-70.72696)));
        paradaRepository.save(new Parada("ELEVADO", ruta2, (long)45, (long)47, new Coordenada(19.48548,-70.73131)));
        paradaRepository.save(new Parada("D POLO B", ruta2, (long)44, (long)46, new Coordenada(19.48669,-70.72843)));
        paradaRepository.save(new Parada("HOSPITAL", ruta2, (long)43, (long)45, new Coordenada(19.48699,-70.72513)));
        paradaRepository.save(new Parada("MARCOS CAMBIO", ruta2, (long)42, (long)44, new Coordenada(19.48732,-70.72185)));
        paradaRepository.save(new Parada("FARMACIA YNA3", ruta2, (long)41, (long)43, new Coordenada(19.48753,-70.71977)));
        paradaRepository.save(new Parada("DELTA COMERCIAL", ruta2, (long)40, (long)42, new Coordenada(19.48944,-70.71812)));


    }
}
