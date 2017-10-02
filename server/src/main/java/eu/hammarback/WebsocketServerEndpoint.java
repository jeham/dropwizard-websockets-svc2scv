package eu.hammarback;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static javax.websocket.CloseReason.CloseCodes.getCloseCode;

@Timed(name = "timed")
@Metered(name = "metered")
@ExceptionMetered(name = "exception")
@ServerEndpoint(value = "/socket/{projectId}")
public class WebsocketServerEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketServerEndpoint.class.getName());
  private static Map<String, Session> SESSIONS = new HashMap<>();

  @OnOpen
  public void onOpen(Session session, @PathParam("projectId") String projectId) throws IOException {
    LOGGER.info("Opened: {}, {}", session.getId(), session);
    LOGGER.info("ProjectId: {}", projectId);
    LOGGER.info("QueryString: {}", session.getQueryString());
    SESSIONS.put(session.getId(), session);
  }

  @OnClose
  public void onClose(Session session, @PathParam("projectId") String projectId, CloseReason closeReason) {
    LOGGER.info("Closed: {}, reason: {}, {}", session.getId(), getCloseCode(closeReason.getCloseCode().getCode()), projectId);
    SESSIONS.remove(session.getId());
  }

  @OnError
  public void onError(Session session, @PathParam("projectId") String projectId, Throwable throwable) {
    LOGGER.info("Error: {}, exception: {}, {}", session.getId(), throwable.getMessage(), projectId);
  }

  @OnMessage
  public void onMessage(Session session, String message, @PathParam("projectId") String projectId) {
    try {
      LOGGER.info("Received message: {} from {}", message, projectId);
      session.getBasicRemote().sendText("Pong!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void sendMessageToAll(String text) {
    SESSIONS.values().forEach(session -> {
      session.getAsyncRemote().sendText(text, result -> {
        if (!result.isOK()) {
          LOGGER.error("Failed to send messages", result.getException());
        }
      });
    });
  }

}