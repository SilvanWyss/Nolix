//package declaration
package ch.nolix.core.nolixlicense;

//own imports
import ch.nolix.core.license.License;

//class
public final class Nolix2023Ultimate extends License {

  //method
  //filteredKey = '48956380'
  @Override
  protected boolean acceptsFilteredKey(final String filteredKey) {
    try {
      final var keyAsInt = Integer.valueOf(filteredKey);
      return (keyAsInt / 20 - keyAsInt % 260 - 9 * keyAsInt + 438_159_801 == 0);
    } catch (final Throwable error) { //NOSONAR: This method just discovers if the given filteredKey would work.
      return false;
    }
  }
}
