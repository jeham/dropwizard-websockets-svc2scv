package eu.hammarback;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.websockets.WebsocketBundle;

public class ServerApplication extends Application<Configuration> {

  public void initialize(Bootstrap<Configuration> bootstrap) {
    bootstrap.addBundle(new WebsocketBundle(WebsocketServerEndpoint.class));
  }

  @Override
  public void run(Configuration configuration, Environment environment) throws Exception {
    environment.jersey().register(new MessageResource());
  }

  public static void main(String[] args) throws Exception {
    new ServerApplication().run(args);
  }

}
