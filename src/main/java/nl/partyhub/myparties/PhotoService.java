package nl.partyhub.myparties;

import Connectivity.DbManager;
import Connectivity.Querymanager;
import Models.Foto;
import Models.Party;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Maiko
 */
@Path("photos")
public class PhotoService {

    private DbManager dbManager;
    private Querymanager querymanager;


    @GET
    @Produces("text/plain")
    public String schijt() {
        
        return "Alle phototjes";

    }

    @GET
    @Path("{id : \\d+}") //support digit only
    public Response getPhotoById(@PathParam("id") String id) {
return null;
    }


    @GET
    @Path("{name}")
    public Response getPartyPictures(@PathParam("name") String name) {
        dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);
        
        querymanager.addParty(name);
        
        return Response.status(200)
                .entity("party is toegevoegd met naam: " + name).build();

    }
    
//    @Path("/helloworld")
//public class HelloWorldResource {
//
//    // The Java method will process HTTP GET requests
//     @GET
//    // The Java method will produce content identified by the MIME Media
//    // type "text/plain"
//     @Produces("text/plain")
//     public String getClichedMessage() {
//    // Return some cliched textual content
//     return "Hello Android";
////     }
    
    
    
    @GET
    @Path("/{id}/{photo}")
    public Response getPartyPicture(@PathParam("id") int feestId, @PathParam("photo") int fotoId) {
        
                dbManager = new DbManager();
        dbManager.openConnection();
        querymanager = new Querymanager(dbManager);
        Party party = querymanager.getParty(feestId);
        Foto foto = querymanager.getFoto(feestId, fotoId);
        if (querymanager.checkIfPartyExists(feestId)){
            querymanager.getFoto(feestId,fotoId);
            
            return Response.status(200).entity("picture from party "+party.getFeestNaam()+" with id:"+foto.getId()+" ").build();
        }else{
            return Response.status(200).entity("party id : "+feestId+" does not exist").build();
        }}
        
        
        
        
            
}
