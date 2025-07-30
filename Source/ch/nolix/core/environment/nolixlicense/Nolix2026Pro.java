package ch.nolix.core.environment.nolixlicense;

import ch.nolix.core.misc.license.License;

public final class Nolix2026Pro extends License {

  //key = '45680060'
  @Override
  protected boolean acceptsRefinedKey(final String filteredKey) {
    try {
      final var keyAsInt = Integer.valueOf(filteredKey);
      return (-3 * keyAsInt + keyAsInt / 20 - keyAsInt % 250 + 134_756_237 == 0);
    } catch (final Throwable error) { //NOSONAR: This method just discovers if the given filteredKey would work.
      return false;
    }
  }
}
