/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.nolixlicense;

import ch.nolix.base.environment.license.AbstractLicense;

/**
 * @author Silvan Wyss
 */
public final class NolixPremium2027 extends AbstractLicense {
  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean acceptsRefinedKey(final String refinedKey) { // refinedKey = 45680061
    try {
      final var k = Long.valueOf(refinedKey);

      return (377 * k) + (k % 971) + (k % 843) - 17_221_383_754L == 0;
    } catch (final RuntimeException _) {
      return false;
    }
  }
}
