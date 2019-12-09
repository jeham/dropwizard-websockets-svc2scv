package eu.hammarback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint(configurator = CustomConfigurator.class)
public class WebsocketClientEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketClientEndpoint.class.getName());

  @OnOpen
  public void onOpen(Session session) {
    LOGGER.info("open: " + session);
  }

  @OnClose
  public void onClose(Session session) {
    LOGGER.info("close: " + session);
  }

  @OnError
  public void onError(Throwable error) {
    LOGGER.info("error: " + error);
  }

  @OnMessage
  public void onMessage(String message) {
    LOGGER.info("message: " + message);
  }

}
