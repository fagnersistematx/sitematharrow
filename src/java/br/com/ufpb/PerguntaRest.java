package br.com.ufpb;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author t1061605
 */
@Path("/pergunta")
public class PerguntaRest {
    //@EJB
    //private NameStorageBean nameStorage;
    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     *
     * @return an instance of java.lang.String
     */

    private PerguntaJpaController pjc = new PerguntaJpaController();

    @GET
    @Produces("application/json")
    public List<Pergunta> getPergunta() {
        return pjc.findPerguntaEntities();
    }

    @Path("{perguntaid}")
    @GET
    @Produces("application/json")
    public void deletePergunta(@PathParam("perguntaid") int id) {       
        pjc.destroy(id);
    } 
    
    @PUT
    @Produces("application/json")
    public void updatePergunta(Pergunta pergunta) {
        pjc.edit(pergunta);
    }

    @POST
    @Produces("application/json")
    public void creatPergunta(Pergunta pergunta) {
        pjc.create(pergunta);
    }
}
