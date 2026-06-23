/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.http;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.baseextendediterable.StoringRequestable;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;

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

    Validator.assertThat(content).thatIsNamed(LowerCaseVariableNameCatalog.CONTENT).isNotBlank();

    this.content = content;
  }

  public String getContent() {
    return content;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return HTTP_HEADER + "\n";
  }
}
