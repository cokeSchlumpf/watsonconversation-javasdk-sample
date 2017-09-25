package ws;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.About;
import util.AbstractService;

@Stateless
@Path("about")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AboutService extends AbstractService {

	@GET
	public About about() {
		return new About("1.0", "wcs-poc");
	}

}