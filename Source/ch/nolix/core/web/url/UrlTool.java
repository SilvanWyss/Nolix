package ch.nolix.core.web.url;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.web.url.IUrlTool;

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

    Validator.assertThat(url).thatIsNamed(LowerCaseVariableCatalog.URL).isNotNull();

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
