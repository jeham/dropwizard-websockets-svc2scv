package eu.hammarback;

import com.google.common.collect.ImmutableList;

import javax.websocket.ClientEndpointConfig;
import java.util.List;
import java.util.Map;

public class CustomConfigurator extends ClientEndpointConfig.Configurator {

  @Override
  public void beforeRequest(Map<String, List<String>> headers) {
    // Use headers.put() to add custom headers to requests
    super.beforeRequest(headers);
  }

}
