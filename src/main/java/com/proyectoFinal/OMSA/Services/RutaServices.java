package com.proyectoFinal.OMSA.Services;

import com.proyectoFinal.OMSA.Entities.*;
import com.proyectoFinal.OMSA.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anyderre on 01/07/17.
 */
@Service
public class RutaServices {
    @Autowired
    RutaRepository rutaRepository;

    public List<Ruta> buscarRutaPorNombreCorredor(String nombre){
        return rutaRepository.findAllByHabilitadoIsTrueAndNombreCorredor(nombre);
    }

    public List<Ruta> buscarRutasPorPagina(int page, int itemPerPage){
        Pageable pageable = new PageRequest(page, itemPerPage);
        return rutaRepository.findAllByHabilitadoIsTrue(pageable);
    }

    public List<Ruta>buscarTodasLasRutas(){
        return rutaRepository.findAllByHabilitadoIsTrue();
    }

    public Ruta buscarRutaPorId(Long id){
        return rutaRepository.findRutaByIdAndHabilitadoIsTrue(id);
    }

    public Ruta guardarRuta(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    @Transactional
    public void eliminarRutaPorId(Long id){
         rutaRepository.deleteById(id);
    }

    @Transactional
    public void modificarRutaPorId(Ruta ruta){
         rutaRepository.modifyRutaById(ruta.getDistanciaTotal(), ruta.getEsDireccionSubida(),ruta.getFechaUltimaModificacion(),ruta.getNombreCorredor(),ruta.getId());
    }

    public void guardarRutaAlPrincipio(){
        Ruta ruta = new Ruta();
        ruta.setDistanciaTotal((float) 43233);
        ruta.setFechaCreada((long) 1500573374);
        ruta.setFechaUltimaModificacion((long) 1500573374);
        ruta.setCiudad("Santiago de Los Caballeros");
        ruta.setNombreCorredor("C-3(Baldom)");
        ruta.setEsDireccionSubida(true);

        ArrayList<Coordenada> coordenadas = new ArrayList<>();

        coordenadas.add(new Coordenada(19.48827848, -70.71671963));
        coordenadas.add( new Coordenada(19.48763117, -70.7144022));
        coordenadas.add(new Coordenada(19.48263467, -70.70815265));
        coordenadas.add( new Coordenada(19.47703113, -70.69886684));
        coordenadas.add(new Coordenada(19.47478561, -70.69486499));
        coordenadas.add( new Coordenada(19.47148808, -70.69198966));
        coordenadas.add( new Coordenada(19.47100254, -70.69130301));
        coordenadas.add(new Coordenada(19.46970779, -70.68789124));
        coordenadas.add(new Coordenada(19.46914133, -70.68726897));
        coordenadas.add( new Coordenada(19.46072512, -70.68602443));
        coordenadas.add( new Coordenada(19.45645615, -70.68546653));
        coordenadas.add( new Coordenada(19.45366407, -70.68495154));
        coordenadas.add( new Coordenada(19.45218707, -70.68516612));
        coordenadas.add( new Coordenada(19.44777625, -70.68817019));
        coordenadas.add( new Coordenada(19.44684551, -70.68844914));
        coordenadas.add(new Coordenada(19.44387115, -70.68722606));
        coordenadas.add(new Coordenada(19.4414633, -70.68714023));
        coordenadas.add(new Coordenada(19.44077534, -70.68731189));
        coordenadas.add(new Coordenada(19.41610787, -70.70278287));
        coordenadas.add(new Coordenada(19.4155817, -70.70394158));
        coordenadas.add( new Coordenada(19.41448887, -70.71085095));
        coordenadas.add( new Coordenada(19.41351747, -70.72767377));
        coordenadas.add(new Coordenada(19.41398294, -70.72891831));
        coordenadas.add( new Coordenada(19.42074216, -70.73591352));
        coordenadas.add(new Coordenada(19.42810818, -70.72795272));
        coordenadas.add( new Coordenada(19.43053647, -70.72602153));
        coordenadas.add( new Coordenada(19.43268142, -70.72499156));
        coordenadas.add( new Coordenada(19.44101815, -70.72207332));
        coordenadas.add( new Coordenada(19.4422929, -70.72226644));
        coordenadas.add(new Coordenada(19.44488285, -70.72406888));
        coordenadas.add(new Coordenada(19.4456922, -70.72421908));
        coordenadas.add(new Coordenada(19.44830232, -70.72372556));
        coordenadas.add( new Coordenada(19.45068984, -70.7241118));
        coordenadas.add(new Coordenada(19.45164079, -70.72434783));
        coordenadas.add(new Coordenada(19.45475663, -70.72449803));
        coordenadas.add(new Coordenada(19.45524221, -70.72144568));
        coordenadas.add(new Coordenada(19.45518151, -70.72078049));
        coordenadas.add(new Coordenada(19.45423564, -70.71545631));
        coordenadas.add(new Coordenada(19.45407378, -70.71541876));
        coordenadas.add(new Coordenada(19.45329482, -70.71546704));
        coordenadas.add(new Coordenada(19.45321389, -70.71533829));
        coordenadas.add(new Coordenada(19.45601104, -70.71514517));
        coordenadas.add(new Coordenada(19.45688609, -70.71499497));
        coordenadas.add(new Coordenada(19.45841868, -70.71481794));
        coordenadas.add(new Coordenada(19.45918749, -70.714598));
        coordenadas.add(new Coordenada(19.4630821, -70.71291894));
        coordenadas.add(new Coordenada(19.46372445, -70.71277946));
        coordenadas.add(new Coordenada(19.46434657, -70.71285993));
        coordenadas.add(new Coordenada(19.46518617, -70.71311206));
        coordenadas.add(new Coordenada(19.46782632, -70.71404278));
        coordenadas.add(new Coordenada(19.4685951, -70.71483672));
        coordenadas.add(new Coordenada(19.46914133, -70.71574867));
        coordenadas.add(new Coordenada(19.47049678, -70.71673572));
        coordenadas.add(new Coordenada(19.47144762, -70.71789443));
        coordenadas.add(new Coordenada(19.47196349, -70.71895659));
        coordenadas.add(new Coordenada(19.47292443, -70.72001874));
        coordenadas.add(new Coordenada(19.47432031, -70.72096288));
        coordenadas.add(new Coordenada(19.47577688, -70.7229048));
        coordenadas.add(new Coordenada(19.47839663, -70.7252866));
        coordenadas.add(new Coordenada(19.47928673, -70.72593033));
        coordenadas.add(new Coordenada(19.48617472, -70.73182046));
        coordenadas.add(new Coordenada(19.48748957, -70.7186991));
        coordenadas.add(new Coordenada(19.48945172, -70.71881711));
        coordenadas.add(new Coordenada(19.48910784, -70.7170254));
        coordenadas.add(new Coordenada(19.48827848, -70.71671963));
        ruta.setCoordenadas(coordenadas);
        rutaRepository.save(ruta);
        ruta.setEsDireccionSubida(false);
        rutaRepository.save(ruta);

    }
}
