
package nl.partyhub.myparties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
/**
 *
 * @author Maiko
 */
@Path("videos")
public class VideoService  {
    	@GET
        @Produces("text/plain")
        public String videos() {
            return "Alle videotjes";
	}
        
        @GET
	@Path("{id : \\d+}") //support digit only
	public Response getVideoById(@PathParam("id") String id) {
 
	   return Response.status(200).entity("getVideosById is called, id : " + id).build();
 
	}
        
}