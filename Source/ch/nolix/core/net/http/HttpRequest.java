package ch.nolix.core.net.http;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.StoringRequestable;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public record HttpRequest(String content) {

  public static final String ACCEPT_HEADER = "Accept";

  public static final String CONTENT_TYPE_HEADER = "Content-Type";

  public static final String HOST_HEADER = "Host";

  public static final String HTTP_HEADER = "HTTP";

  public static boolean canBe(final StoringRequestable<String> lines) {
    return lines.containsAny(l -> l.contains(HTTP_HEADER))
    && lines.containsAny(l -> l.contains(HOST_HEADER))
    && lines.containsAny(l -> l.contains(ACCEPT_HEADER + ": text/html"));
  }

  public HttpRequest(final String content) { //NOSONAR: This constructor does more than the default one.

    GlobalValidator.assertThat(content).thatIsNamed(LowerCaseVariableCatalogue.CONTENT).isNotBlank();

    this.content = content;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    return HTTP_HEADER + "\n";
  }
}
