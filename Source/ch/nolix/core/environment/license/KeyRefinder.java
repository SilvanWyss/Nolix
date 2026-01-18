/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.environment.license;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * Of the {@link KeyRefinder} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class KeyRefinder {
  /**
   * Prevents that an instance of the {@link KeyRefinder} can be created.
   */
  private KeyRefinder() {
  }

  /**
   * @param key
   * @return a refined key from the given key.
   * @throws RuntimeException if the given key is null.
   */
  public static String getRefinedKeyFromKey(final String key) {
    Validator.assertThat(key).thatIsNamed(LowerCaseVariableCatalog.KEY).isNotNull();

    return //
    key
      .replace(StringCatalog.MINUS, StringCatalog.EMPTY_STRING)
      .replace(StringCatalog.SPACE, StringCatalog.EMPTY_STRING);
  }
}
