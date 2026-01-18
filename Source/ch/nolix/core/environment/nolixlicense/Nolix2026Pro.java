/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.environment.nolixlicense;

import ch.nolix.core.environment.license.License;

/**
 * @author Silvan Wyss
 */
public final class Nolix2026Pro extends License {
  //key = '45680060'
  @Override
  protected boolean acceptsRefinedKey(final String filteredKey) {
    try {
      final var keyAsInt = Integer.valueOf(filteredKey);
      return (-3 * keyAsInt + keyAsInt / 20 - keyAsInt % 250 + 134_756_237 == 0);
    } catch (final Throwable _) { //NOSONAR: This method just checks if the given filteredKey is valid.
      return false;
    }
  }
}
