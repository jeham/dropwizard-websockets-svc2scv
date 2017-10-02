package eu.hammarback;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;

public class ClientApplication extends Application<Configuration> {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public void run(Configuration configuration, Environment environment) throws Exception {
    environment.jersey().disable();

    WebSocketContainer container = ContainerProvider.getWebSocketContainer();

    String projectId = UUID.randomUUID().toString();
    String someTestValue = "foobar";
    Session session = container.connectToServer(WebsocketClientEndpoint.class,
        URI.create(format("ws://localhost:8080/socket/%s?param=%s", projectId, someTestValue)));

    logger.info("Session isOpen?: " + session.isOpen());
  }

  public static void main(String[] args) throws Exception {
    new ClientApplication().run(args);
  }

}
