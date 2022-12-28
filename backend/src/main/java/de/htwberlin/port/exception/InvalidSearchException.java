package de.htwberlin.port.exception;

import de.htwberlin.core.domain.model.SearchParam;
import java.util.stream.Stream;

public class InvalidSearchException extends RuntimeException {

  public InvalidSearchException() {
    super("The query params for the search are invalid");
  }

  public InvalidSearchException(String message) {
    super(message);
  }

  public InvalidSearchException(SearchParam... params) {
    super(
        "The query params for the search are invalid: "
            + String.join(
                " ",
                Stream.of(params).map(p -> p.getParam() + ":\"" + p.getValue() + "\"").toList()));
  }
}
