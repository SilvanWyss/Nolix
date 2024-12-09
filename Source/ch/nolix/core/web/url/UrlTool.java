package ch.nolix.core.web.url;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.webapi.urlapi.IUrlTool;

/**
 * @author Silvan Wyss
 * @version 2024-12-09
 */
public final class UrlTool implements IUrlTool {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDisplayTextForUrl(final String url) {

    GlobalValidator.assertThat(url).thatIsNamed(LowerCaseVariableCatalogue.URL).isNotNull();

    if (url.startsWith("http://www.")) {
      return url.substring(11);
    }

    if (url.startsWith("https://www.")) {
      return url.substring(12);
    }

    if (url.startsWith("http://")) {
      return url.substring(7);
    }

    if (url.startsWith("https://")) {
      return url.substring(8);
    }

    if (url.startsWith("www.")) {
      return url.substring(4);
    }

    return url;
  }
}
