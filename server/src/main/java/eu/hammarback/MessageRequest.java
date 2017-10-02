package eu.hammarback;

import org.hibernate.validator.constraints.NotEmpty;

public class MessageRequest {

  @NotEmpty
  public String text;

}
