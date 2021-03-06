package ntou.bernie.easylearn.image.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import ntou.bernie.easylearn.image.core.ImgItem;
import org.mongodb.morphia.Datastore;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bernie on 2016/3/23.
 */
@Path("/image")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResources {
    private Datastore datastore;
    public ImageResources(Datastore datastore){
        this.datastore = datastore;
    }

    @POST
    @Timed
    @ExceptionMetered
    public Response sync(ImgItem imgItem) {
        datastore.save(imgItem);
        return Response.ok().build();
    }
}
