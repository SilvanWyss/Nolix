/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.url;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.web.url.IUrlTool;

/**
 * @author Silvan Wyss
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
