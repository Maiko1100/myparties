/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import nl.partyhub.myparties.PhotoService;

/**
 *
 * @author Maiko
 */
@ApplicationPath("resources")
public class RESTConfig extends Application{
       public Set<Class<?>> getClasses() {
       Set<Class<?>> s = new HashSet<Class<?>>();
       s.add(PhotoService.class);
       return s;
    
}
}
