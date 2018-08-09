package com.proyectoFinal.OMSA.Controladores; /**
 * Created by anyderre on 04/07/17.
 */
import com.proyectoFinal.OMSA.Entities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.proyectoFinal.OMSA.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
    private static final String ACCECPT_TYPE= "application/json";
    private static final String CONTENT_TYPE= "application/json";
    @Autowired
    private AutobusServices autobusServices;
    @Autowired
    private ParadaServices paradaServices;
    @Autowired
    private ChequeoServices chequeoServices;
    @Autowired
    private CoordenadaServices coordenadaServices;
    @Autowired
    private RutaServices rutaServices;
    @Autowired
    UsuarioServices usuarioServices;
    @Autowired
    UserRatingServices userRatingServices;
    @Autowired
    RolServices rolServices;


//**********************************************************************Autobus*****************************************************
    /**
     * Buscar un autobus por su id
     * @param id
     * @return
     */
    @RequestMapping(value = "/autobus/buscar/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Autobus autobus(@PathVariable Long id){
        Autobus autobus = autobusServices.buscarUnAutobus(id);
        if (autobus==null){
            return new Autobus();
        }
        autobus.getRuta().setParadas(null);
        return autobus;
    }

    @RequestMapping(value = "/autobuses/buscar", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Autobus> buscarTodosLoasAutobuses(){
        List<Autobus> autobuses = autobusServices.buscarTodoLosAutobus();

        if (autobuses==null){
            return new ArrayList<>();
        }
        for(int i=0; i<autobuses.size(); i++){
            autobuses.get(i).setRuta(null);
            autobuses.get(i).setUltimaParada(null);
        }
        return autobuses;
    }

    @RequestMapping(value = "/autobus/buscar/ruta/size/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public int getCantdeAutobusPorRuta(@PathVariable("id")Long id){
        return autobusServices.buscarTodosLosAutobusporRuta(id).size();
    }
    @RequestMapping(value = "/autobus/buscar/size/", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public int getCantAutobus(@PathVariable("id")Long id){
        return autobusServices.buscarAutobusesNull();
    }


    @RequestMapping(value = "/autobus/buscar/{page}/{items}/corredor/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> buscarAutobusPorRutaId(@PathVariable ("page") int page, @PathVariable ("items") int itemsPerPage, @PathVariable ("id")Long corredor){
        List<Autobus> autobuses = autobusServices.buscarAutobusPorRutaId(corredor, page, itemsPerPage);
        if (autobuses==null){
            return new ArrayList<>();
        }
        return (ArrayList<Autobus>) autobuses;
    }
    @RequestMapping(value = "/autobus/sinRuta/buscar/{page}/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> buscarAutobusSinRuta(@PathVariable ("page") int page, @PathVariable ("items") int itemsPerPage){
        List<Autobus> autobuses = autobusServices.buscarAutobusPorRutaNull(page, itemsPerPage);
        if (autobuses==null){
            return new ArrayList<>();
        }
        return (ArrayList<Autobus>) autobuses;
    }
    /**
     *
     * Obtener Listado de autobus de una Ruta
     * @param id
     * @return
     */
    @RequestMapping(value ="/autobuses/buscar/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Autobus> obetenerAutobusPorRuta(@PathVariable("id")Long id){
        Ruta ruta =rutaServices.buscarRutaPorId(id);
        if(ruta==null){
//            logger.error("No se encuentra el autobus buscado.", id);
            return new ArrayList<Autobus>();
        }
        List <Autobus> autobuses = autobusServices.buscarTodosLosAutobusporRuta(ruta.getId());
        for(Autobus autobus: autobuses){
            autobus.getRuta().setParadas(null);
        }
        return (ArrayList<Autobus>) autobuses;
    }

    /**
     *
     * guardar un autobus
     * @param id
     * @return
     */
    @RequestMapping(value ="/autobus/guardar/{id_ruta}", method = RequestMethod.POST, produces =ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String guardarAutobus(@RequestBody Autobus autobus, @PathVariable("id_ruta")Long id){

        Ruta ruta = rutaServices.buscarRutaPorId(id);
        autobus.setRuta(ruta);
        Autobus autobus1 = autobusServices.guardarAutobus(autobus);
        if(ruta==null){
            return "La ruta no existe";
        }
        if(autobus1!=null){

            return "autobus guardada";
        }
        return "Error al guardar el autobus";
    }

    /**
     *
     * modificar coordenada de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/posicion/", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCoordenadaAutobus(@RequestBody Autobus autobus) {
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());

        if(currentAutobus == null){
            return new Gson().toJson("Este autobus no existe");
        }
        if(currentAutobus.getCoordenada()==null){
            Coordenada coordenada = new Coordenada();
            coordenada.setLongitud(autobus.getCoordenada().getLongitud());
            coordenada.setLatitude(autobus.getCoordenada().getLatitude());
            currentAutobus.setCoordenada(coordenada);
            currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
            autobusServices.guardarAutobus(currentAutobus);
        }
        currentAutobus.getCoordenada().setLatitude(autobus.getCoordenada().getLatitude());
        currentAutobus.getCoordenada().setLongitud(autobus.getCoordenada().getLongitud());
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
        autobusServices.guardarAutobus(currentAutobus);

        return new Gson().toJson("Posicion autobus modificado exitosamente");
    }
    /**
     *
     * modificar posicion de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/estado", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes =ACCECPT_TYPE)
    public String modificarEstadoAutobus(@RequestBody Autobus autobus){
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());
        if(currentAutobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        currentAutobus.setActivo(autobus.getActivo());
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());

        autobusServices.guardarAutobus(currentAutobus);
        return new Gson().toJson( "Estado Autobus modificado exitosamente");

    }
    /**
     * modificar posicion de un autobus
     * @param autobus
     * @return
     */
    @RequestMapping(value = "/autobus/modificar/cantidadPasajeros", method =RequestMethod.PUT, produces = ACCECPT_TYPE, consumes = ACCECPT_TYPE)
    public String modificarCantidadPasajerosAutobus( @RequestBody Autobus autobus){
        Autobus currentAutobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(autobus.getRaspberryPiNumeroSerial());
        if(currentAutobus == null){
            return new Gson().toJson("El autobus que quieres modificar no existe");
        }
        if(autobus.getCantidadDePasajerosActual()>=currentAutobus.getCantidadDeAsientos()){
            currentAutobus.setCantidadDePasajerosActual(currentAutobus.getCantidadDeAsientos());

        }else {
            currentAutobus.setCantidadDePasajerosActual(autobus.getCantidadDePasajerosActual());
        }
        currentAutobus.setUltimaFechaModificada(autobus.getUltimaFechaModificada());
        autobusServices.guardarAutobus(currentAutobus);
        return new Gson().toJson("Autobus modificado exitosamente");

    }

    @RequestMapping(value = "/autobus/eliminar/{id}", method = RequestMethod.POST)
    public Boolean eliminarAutobus(@PathVariable("id")Long id){
        Autobus autobus = autobusServices.buscarUnAutobus(id);
        List<Chequeo> chequeos = chequeoServices.buscarChequeoPorAutobusId(autobus.getId());
        for(Chequeo chequeo: chequeos){
            chequeo.setAutobus(null);
            chequeoServices.guardarChequeo(chequeo);
        }
        if(autobus==null){
            return false;
        }
        autobusServices.eliminarAutobusporId(id);
        return true;
    }
    //----------------------------------------Parada---------------------------------------
    @RequestMapping(value = "/paradas/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Parada> buscarParadasPorRuta(@PathVariable Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta==null){
            return new ArrayList<>();
        }
        List<Parada> paradas = paradaServices.buscarParadaPorRutaId(id);
        if(paradas.isEmpty()){
            return new ArrayList<>();
        }
        List<Parada> paradasTemp= new ArrayList<>();
        for(Parada parada: paradas){
            parada.setRuta(null);
            paradasTemp.add(parada);
        }
        return new ArrayList<>(paradasTemp);
    }
    /** buscar una parada
     * @param id
     * @return
     */
    @RequestMapping(value = "/parada/buscar/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Parada buscarParada(@PathVariable Long id){
        Parada parada =paradaServices.buscarParada(id);

        if(parada==null){
            return new Parada();
        }
        return parada;
    }
    /** Guardar una parada
     * @param id
     * @param parada
     * @return
     */
    @RequestMapping(value = "/paradas/guardar/{ruta_id}", method = RequestMethod.POST, produces = ACCECPT_TYPE)
    public String guardarParada(@RequestBody Parada parada, @PathVariable("ruta_id") Long id ){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta!=null){
            parada.setRuta(ruta);
            paradaServices.guardarParada(parada);
            return new Gson().toJson("Parada guardada exitosamente");
        }
        return new Gson().toJson("no se pudo guardar la parada");
    }

    /** Buscar rango de paradas
     * @param id_ruta
     * @param page
     * @param numberOfItems
     * @return
     */
    @RequestMapping(value = "/paradas/buscar/{page}/{items}/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Parada> buscarTopParadas(@PathVariable("page")int page, @PathVariable("items")int numberOfItems, @PathVariable("id")Long id_ruta){
        List<Parada> paradas = paradaServices.getTopParadas(id_ruta, page, numberOfItems);
        if(paradas==null){
            return new ArrayList<>();
        }
        return (ArrayList<Parada>) paradas;
    }

    @RequestMapping(value = "/paradas/mas/cerca/{LatOrig}/{LngOrig}/{LatDest}/{LngDest}/", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ParadaCercana buscarParadasMasCercanas(@PathVariable("LatOrig") Double latOrig, @PathVariable("LngOrig") Double lngOrig,
                                                  @PathVariable("LatDest") Double latDest, @PathVariable("LngDest") Double lngDest){
        List<Parada> paradas = paradaServices.buscarTodasParadas();
        ArrayList<ParadaCercana> listaOrigen  = new ArrayList<>();
        ArrayList<ParadaCercana> listaDestino  = new ArrayList<>();
        Coordenada coordenadaOrig = new Coordenada(latOrig, lngOrig);
        Coordenada coordenadaDest = new Coordenada(latDest, lngDest);
        for(Parada parada : paradas){
            double distanciaOrigen = getDistance(parada.getCoordenada(),coordenadaOrig);
            double distanciaDestino = getDistance(parada.getCoordenada(),coordenadaDest);
            listaDestino.add(new ParadaCercana(parada, distanciaDestino, coordenadaDest, null, null));
            listaOrigen.add(new ParadaCercana(parada, distanciaOrigen, coordenadaOrig,null,null));
        }

        listaDestino = mergeSort(listaDestino);
        listaOrigen = mergeSort(listaOrigen);
        for (ParadaCercana paradaOrigen : listaOrigen){
            for(ParadaCercana paradaDestino:listaDestino) {

                if(paradaDestino.getParadaActual().getRuta().getId().equals(paradaOrigen.getParadaActual().getRuta().getId())){
                    paradaDestino.getParadaActual().getRuta().setParadas(null);
                    paradaOrigen.getParadaActual().getRuta().setParadas(null);
                    return new ParadaCercana(null, 0.0, null,paradaOrigen.getParadaActual(),paradaDestino.getParadaActual());
                }
            }
        }
        return new ParadaCercana();

    }

    private double getDistance(Coordenada parada, Coordenada coordenada) {

        double distanciaActual;

        distanciaActual = Math.sqrt(
                Math.pow((parada.getLatitude() - coordenada.getLatitude()), 2)
                        + Math.pow((parada.getLongitud() - coordenada.getLongitud()), 2)
        );
       return distanciaActual;
    }

    private ArrayList<ParadaCercana> mergeSort(ArrayList<ParadaCercana> whole) {
        ArrayList<ParadaCercana> left = new ArrayList<>();
        ArrayList<ParadaCercana> right = new ArrayList<>();
        int center;

        if (whole.size() == 1) {
            return whole;
        } else {
            center = whole.size()/2;
            // copy the left half of whole into the left.
            for (int i=0; i<center; i++) {
                left.add(whole.get(i));
            }

            //copy the right half of whole into the new arraylist.
            for (int i=center; i<whole.size(); i++) {
                right.add(whole.get(i));
            }

            // Sort the left and right halves of the arraylist.
            left  = mergeSort(left);
            right = mergeSort(right);

            // Merge the results back together.
            merge(left, right, whole);
        }
        return whole;
    }
    private void merge(ArrayList<ParadaCercana> left, ArrayList<ParadaCercana> right, ArrayList<ParadaCercana> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).getDistancia().compareTo(right.get(rightIndex).getDistancia())) < 0) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex));
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<ParadaCercana> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        // Copy the rest of whichever ArrayList (left or right) was not used up.
        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i));
            wholeIndex++;
        }
    }

    //---------------------------------------Ruta-------------------------------------------//--------------------------------------Ruta----------------------------------------------------------
    @RequestMapping(value="/guardar/ruta/", method =RequestMethod.POST, consumes = ACCECPT_TYPE)
    public String guardarRuta(@RequestBody Ruta ruta){
        Ruta ruta1  = rutaServices.guardarRuta(ruta);

        if(ruta1!=null){
        return  new Gson().toJson("Ruta guardada exitosamente");
    }
    return new Gson().toJson("no se pudo guardar la ruta especificada");
}
    @RequestMapping(value = "/ruta/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Ruta buscarRuta(@PathVariable Long id){
        Ruta ruta =rutaServices.buscarRutaPorId(id);
        if(ruta==null){
            Ruta ruta1 = new Ruta();
            ruta1.setParadas(null);
            return ruta1;
        }
        ruta.setParadas(null);
        List <Coordenada> coordenadas = new ArrayList<>();
        for(Coordenada coordenada: ruta.getCoordenadas()){
            if(coordenada.getHabilitado()){
                coordenadas.add(coordenada);
            }
        }
        ruta.setCoordenadas(coordenadas);
        return  ruta;
    }
    @RequestMapping(value = "/ruta/eliminar/{id}", method = RequestMethod.POST, produces = CONTENT_TYPE)
    public Boolean borrarRuta(@PathVariable("id")Long id){
        Ruta ruta = rutaServices.buscarRutaPorId(id);
        if(ruta!=null){
            paradaServices.eliminarParadaPorRutaId(id);
            rutaServices.eliminarRutaPorId(id);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/ruta/buscar/pagina/{numero}/item/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE )
    public ArrayList<Ruta> buscarRutasPorPagina(@PathVariable("numero")int page, @PathVariable("items")int numberOfItems ){
        List<Ruta> rutas = rutaServices.buscarRutasPorPagina(page, numberOfItems);
        if(rutas==null){
            return new ArrayList<>();
        }
        return (ArrayList<Ruta>) rutas;
    }

    /** Buscar Todas las rutas
     * @return
     */
    @RequestMapping(value = "/rutas/buscar", method = RequestMethod.GET, produces = ACCECPT_TYPE )
    public List<Ruta> buscarTodasLasRutas(){
        List<Ruta> rutas = rutaServices.buscarTodasLasRutas();
        if(rutas==null){
            return new ArrayList<>();
        }
        int cont =0;
        for (Ruta ruta: rutas){
            if(ruta.getCoordenadas().size()!=0){
                for(int i=0; i<ruta.getCoordenadas().size(); i++){
                    if(!ruta.getCoordenadas().get(i).getHabilitado()){
                        rutas.get(cont).getCoordenadas().remove(i);
                    }
                }

            }

            cont++;
        }

        for(Ruta r:rutas){
            r.setParadas(null);
        }
        return rutas;
    }

//-------------------------------------Chequeo-----------------------------------------------


    @RequestMapping(value="/chequeo/guardar", method = RequestMethod.POST, consumes = ACCECPT_TYPE)
    public String guardarChequeo(@RequestBody Chequeo chequeo){
        //obteniendo la parada mas cerca a ese punto
        Autobus autobus = autobusServices.buscarAutobusPorRaspberryNumeroSerial(chequeo.getAutobus().getRaspberryPiNumeroSerial());
        chequeo.setAutobus(autobus);
        Parada parada = getParadaReal(chequeo);
        chequeo.setParada(parada);
        if(parada==null){
            return new Gson().toJson("No se pudo guardar el chequeo");
        }
        autobus.setUltimaParada(parada);
        chequeo.setAutobus(autobus);
        chequeo.setIdRuta(parada.getRuta().getId());
        if(chequeoServices.guardarChequeo(chequeo)==null){
            return new Gson().toJson("No se pudo guardar el chequeo");
        }
        ArrayList<Parada>paradas = (ArrayList<Parada>) paradaServices.buscarParadaPorRutaId(autobus.getRuta().getId());

        return new Gson().toJson("Chequeo guardado");
    }

    @RequestMapping(value="/chequeo/buscar/{id_autobus}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Chequeo> buscarChequeos(@PathVariable("id_autobus")Long id){
       List<Chequeo> chequeos = chequeoServices.buscarChequeoPorAutobusId(id);
        if(chequeos== null){
            return new ArrayList<>();
        }
        return chequeos;
    }

    private Parada getParadaReal(Chequeo chequeo){
        Autobus autobus = chequeo.getAutobus();
        Ruta ruta = autobus.getRuta();
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaServices.buscarParadaPorRutaId(ruta.getId());

        if(paradas==null){
            return null;
        }
        double max=1000000000;
        double distanciaActual;
        Parada paradaActual= new Parada();

        for(Parada parada:paradas) {
            distanciaActual = Math.sqrt(
                    Math.pow((parada.getCoordenada().getLatitude() - chequeo.getParada().getCoordenada().getLatitude()), 2)
                            + Math.pow((parada.getCoordenada().getLongitud() - chequeo.getParada().getCoordenada().getLongitud()), 2)
            );
            if (distanciaActual < max) {
                max = distanciaActual;
                paradaActual = parada;
            }

        }
        return paradaActual;
    }

 //-------------------------------------------------------------Usuario-------------------------------------------------------------------
 @RequestMapping(value = "/usuario/buscar/{page}/item/{items}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
 public List<Usuario> buscarUsuarioPorPaginas(@PathVariable("items")int items, @PathVariable("page")int page){
        ArrayList <Usuario> usuarios = (ArrayList<Usuario>) usuarioServices.buscarUsuarios(page, items);

        if (usuarios==null){
            return new ArrayList<>();
        }
     return  usuarios;
 }
//-----------------------------------------Coordenada----------------------------------------------------
    @RequestMapping(value = "/ruta/{id}/buscar/coordenada/{start}/{end}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public List<Coordenada> buscarCoordenadas (@PathVariable("id")Long id, @PathVariable("start")int start, @PathVariable("end")int end){

       List<Coordenada> coordenadas = rutaServices.buscarRutaPorId(id).getCoordenadas();
       List<Coordenada>coordenadasTemp = new ArrayList<>();
        for(Coordenada coordenada:coordenadas){
            if(coordenada.getHabilitado()){
                coordenadasTemp.add(coordenada);
            }
        }
       if(coordenadasTemp.size()==0){
            return new ArrayList<>();
       }else{
           if(end>=coordenadasTemp.size()){
               end = coordenadasTemp.size();
           }
       }

     return coordenadasTemp.subList(start, end);
    }
//______________________________________________________Mobile App Distance and Time________________________________________________
    @RequestMapping(value = "/distancia/{id}", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public DistanceAndTime buscarDistanciaYTiempo (@PathVariable("id")Long id){
            Parada paradaSeleccionada = paradaServices.buscarParada(id);
            Autobus autobus = autobosMasCercanoPorParada(paradaSeleccionada);
            if(autobus!=null){
                Parada iterador = getParadaMasCerca(autobus);
                return totalTiempoApiGoogle(autobus, paradaSeleccionada, iterador);
            }

            return new DistanceAndTime();
    }


private DistanceAndTime totalTiempoApiGoogle(Autobus autobus, Parada parada, Parada iterador){
    DistanceAndTime distanceAndTime = new DistanceAndTime();
    int cont=0;
    double newDurac= 0.0D, newDuracTraf = 0.0D,newDis= 0.0D;
    int durac=0,dis=0, duracTraf=0;

    Parada iteradorAnterior;
    while (!iterador.getId().equals(parada.getId())) {
        cont++;
        String cadena = "https://maps.googleapis.com/maps/api/directions/json?origin=";
        if(cont ==1){
            cadena = cadena + iterador.getCoordenada().getLatitude();
            cadena = cadena + ",";
            cadena = cadena + iterador.getCoordenada().getLongitud();
            cadena = cadena + "&destination=";
            cadena = cadena + autobus.getCoordenada().getLatitude();
            cadena = cadena + ",";
            cadena = cadena + autobus.getCoordenada().getLongitud();
            cadena = cadena + "&departure_time=1541202457&traffic_model=best_guess&key=AIzaSyCIvewpnbMTDZbCR3NGc4VwKRYb2BB3Qrs";
        }else{
            iteradorAnterior = paradaServices.buscarParada(iterador.getParadaAnterior());
            cadena = cadena + iteradorAnterior.getCoordenada().getLatitude();
            cadena = cadena + ",";
            cadena = cadena + iteradorAnterior.getCoordenada().getLongitud();
            cadena = cadena + "&destination=";
            cadena = cadena + iterador.getCoordenada().getLatitude();
            cadena = cadena + ",";
            cadena = cadena + iterador.getCoordenada().getLongitud();
            cadena = cadena + "&departure_time=1541202457&traffic_model=best_guess&key=AIzaSyCIvewpnbMTDZbCR3NGc4VwKRYb2BB3Qrs";
            iterador= iteradorAnterior;
        }

        URL url = null;


        try {
            url = new URL(cadena);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
            connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                    " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
            int respuesta = connection.getResponseCode();
            StringBuilder result = new StringBuilder();

            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);        // Paso toda la entrada al StringBuilder
                }

                //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                //Accedemos al vector de resultados
                JSONArray resultJSON = respuestaJSON.getJSONArray("routes");   // results es el nombre del campo en el JSON
                JSONObject jsonObject = resultJSON.getJSONObject(0);
                JSONArray jsonArray = jsonObject.getJSONArray("legs");
                //---------------------------distance-----------
                JSONObject distance = jsonArray.getJSONObject(0).getJSONObject("distance");
                 dis = (int) distance.get("value");

                //----------------------------duracion--------------------------
                JSONObject duracion = jsonArray.getJSONObject(0).getJSONObject("duration");
                 durac = (int) duracion.get("value");
                //----------------------------duracion In traffic--------------------------
                JSONObject duracionTraffic = jsonArray.getJSONObject(0).getJSONObject("duration_in_traffic");
                duracTraf = (int) duracionTraffic.get("value");
                newDurac+=durac;
                newDuracTraf+=duracTraf;
                newDis+=dis;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    distanceAndTime.setDistance(newDis/1000);
    distanceAndTime.setDuration(newDurac/60);
    distanceAndTime.setDuration_Traffic(newDuracTraf/60);
    autobus.getRuta().setParadas(null);
    distanceAndTime.setAutobus(autobus);
    return distanceAndTime;

}

    private Parada getParadaMasCerca(Autobus autobus){
        Ruta ruta = autobus.getRuta();
        ArrayList<Parada> paradas = (ArrayList<Parada>) paradaServices.buscarParadaPorRutaId(ruta.getId());

        double max=1000000000;
        double distanciaActual=0;
        int indexes=0;
        for(int i = 0; i<paradas.size(); i++){
            distanciaActual =Math.sqrt(Math.pow((paradas.get(i).getCoordenada().getLatitude()-autobus.getCoordenada().getLatitude()), 2)
                    +Math.pow((paradas.get(i).getCoordenada().getLongitud()-autobus.getCoordenada().getLongitud()),2));
            if (distanciaActual<max){
                max =distanciaActual;
                indexes = i;
            }
        }

        return paradas.get(indexes);
    }
    private Autobus autobosMasCercanoPorParada(Parada parada){
        Ruta ruta = parada.getRuta();
        ArrayList<Autobus>  autobuses = (ArrayList<Autobus>) autobusServices.buscarAutobusActivosYPorRuta(true, ruta);//Busca una lista de autobuses dado una ruta
        if(autobuses.size() == 0){
            return null;
        }

        Parada iterador = paradaServices.buscarParada(parada.getParadaSiguiente());
        while(!iterador.getId().equals(parada.getId())){
            for(Autobus autobus: autobuses){
                if(iterador.getId().equals(autobus.getUltimaParada().getId()))
                    return autobus;
            }
            iterador = paradaServices.buscarParada(iterador.getParadaSiguiente());

        }
        return  null;
    }
//---------------------------------------------------Rating-----------------------------------------------
@RequestMapping(value = "/recibir/comentario/", method = RequestMethod.POST, produces = CONTENT_TYPE)
public UserRating recibirComentario(@RequestBody UserRating userRating){
    UserRating userRatingResult= userRatingServices.guardarComentario(userRating);
    if(userRatingResult!=null){

       return userRatingResult;
    }
    return new UserRating();
}

@RequestMapping(value = "/rating/comentarios/", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<UserRating> buscarUserRating (){
        ArrayList<UserRating> userRatings = (ArrayList<UserRating>) userRatingServices.buscarComentario();
        if(userRatings==null){

            return new ArrayList<>();
        }

        return userRatings;
    }
    @RequestMapping(value = "/rating/comentarios/{page}/{cant}/", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<UserRating> buscarUserRatingPorPagina (@PathVariable("page")int page, @PathVariable("cant") int cant){

        ArrayList<UserRating> userRatings = userRatingServices.buscarUserRatingPorPagina(cant, page);
        if(userRatings==null){

            return new ArrayList<>();
        }
        return userRatings;
    }



///--------------------------------------------------Estadistica---------------------------------------------------------------------------
    /** Buscar Ganancia De Ayer
     * @return
     */
    @RequestMapping(value = "/estadistica/gananciaAyer", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> ganaciaDeAyer (){

    ArrayList<Object[]> ganancias = (ArrayList<Object[]>) chequeoServices.selectGananciaAyer();
    if(ganancias==null){

        return new ArrayList<>();
    }

    return ganancias;
    }
    /** Buscar movimiento mensual
     * @return
     */
    @RequestMapping(value = "/estadistica/movimientoMensual", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> movimientoMensual (){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.movimientoMensual();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }

    /** Buscar movimiento anual
     * @return
     */
    @RequestMapping(value = "/estadistica/movimientoAnual", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> movimientoAnual (){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.selectMovimientoAnual();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }
    /** Buscar Ganancia mensual
     * @return
     */
    @RequestMapping(value = "/estadistica/GananciaMensual", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> gananciaMensual (){

        ArrayList<Object[]> ganancia = (ArrayList<Object[]>) chequeoServices.selectGananciaMensual();
        if(ganancia==null){

            return new ArrayList<>();
        }

        return ganancia;
    }

    /** Buscar Ganancia ultimo mes
     * @return
     */
    @RequestMapping(value = "/estadistica/GananciaUltimoMes", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> gananciaUltimoMes (){

        ArrayList<Object[]> ganancia = (ArrayList<Object[]>) chequeoServices.selectGananciaUltimoMes();
        if(ganancia==null){

            return new ArrayList<>();
        }

        return ganancia;
    }

    /** Buscar movimiento por Ruta Mensual
     * @return
     */
    @RequestMapping(value = "/estadistica/movimientoPorRuta", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> movimientoPorRuta(){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.selectMovimientoPorRuta();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }


    /** Buscar movimiento y ganancia ultimaSemana
     * @return
     */
    @RequestMapping(value = "/estadistica/gananciaUltimaSemana", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> gananciaPorSemana(){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.selectGananciaUltimaSemana();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }

    /** Buscar movimiento por Ruta
     * @return
     */
    @RequestMapping(value = "/estadistica/movimientoEsteMes", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> movimientoPorRutaAnual(){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.selectMovimientoPorRutaAnual();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }

    /** Buscar movimiento por Ruta Ultimos 20 minutos
     * @return
     */
    @RequestMapping(value = "/estadistica/movimientoPorRutaUltimos20Minutos", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public ArrayList<Object[]> movimientoPorRutaUltimos20Minutos(){

        ArrayList<Object[]> movimiento = (ArrayList<Object[]>) chequeoServices.selectMovimientoPorRutaUltimos20Minutos();
        if(movimiento==null){

            return new ArrayList<>();
        }

        return movimiento;
    }

    /** Obtener autobus inactivo
     * @return
     */
    @RequestMapping(value = "/estadistica/autobusInactivo", method = RequestMethod.GET, produces = ACCECPT_TYPE)
    public Integer autobus(){
        return autobusServices.contarAutobusInactivo();
    }
}



