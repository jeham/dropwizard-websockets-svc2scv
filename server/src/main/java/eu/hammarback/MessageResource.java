package eu.hammarback;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("messages")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MessageResource {

  @POST
  public Response sendMessage(@NotNull @Valid MessageRequest message) {
    WebsocketServerEndpoint.sendMessageToAll(message.text);
    return Response.ok().build();
  }

}
