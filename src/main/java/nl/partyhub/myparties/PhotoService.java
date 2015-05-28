package nl.partyhub.myparties;

import Connectivity.DbManager;
import Connectivity.Querymanager;
import Models.Foto;
import Models.Party;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Maiko
 */
@Path("photos")
public class PhotoService{

    private static Map<Integer, Foto> fotos = new HashMap<Integer, Foto>();
    private DbManager dbManager;
    private Querymanager querymanager;

    @GET
    @Produces("text/plain")
    public String Photo() {
        List<Foto> fotos = new ArrayList<Foto>();
        dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);

        fotos = querymanager.getFotoList();
        Gson gson = new Gson();
        String json = gson.toJson(fotos);
        
        dbManager.closeConnection();
        return json;

    }

    @GET
    @Path("{id : \\d+}") //support digit only
    public String getPhotoById(@PathParam("id") int id) {
        dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);

        Party party;
        party = querymanager.getParty(id);
        dbManager.closeConnection();
        return party.getFeestNaam();
    }

    @POST
    @Path("/receivepicture")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createFoto(Foto foto) {
        fotos.put(foto.getId(), foto);
        dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);
        querymanager.addFoto(foto.getFotoNaam(), true, foto.getFeestId(), foto.getFoto(), foto.getGebruikersnaam());
        dbManager.closeConnection();
    }

    @GET
    @Path("/receivepicture2")
    public String getPartyPictures() {
        return null;
//        dbManager = new DbManager();
//        dbManager.openConnection();
//        querymanager = new Querymanager(dbManager);
//
//        querymanager.addParty(name);

    }

    @GET
    @Path("/{id}/{photo}")
    public String getPartyPicture(@PathParam("id") int feestId, @PathParam("photo") int fotoId) {
        dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);

        Foto foto = querymanager.getFoto(feestId, fotoId);

        if (querymanager.checkIfPartyExists(feestId)) {
            dbManager.closeConnection();

            return foto.getFoto();
        } else {
            dbManager.closeConnection();

            return "failed";
        }
    }

}
