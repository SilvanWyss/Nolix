//package declaration
package ch.nolix.core.nolixlicense;

//own imports
import ch.nolix.core.license.License;

//class
public final class Nolix2023Pro extends License {

  //method
  //key = '45680060'
  @Override
  protected boolean acceptsFilteredKey(final String filteredKey) {
    try {
      final var keyAsInt = Integer.valueOf(filteredKey);
      return (-3 * keyAsInt + keyAsInt / 20 - keyAsInt % 250 + 134_756_237 == 0);
    } catch (final Throwable error) { //NOSONAR: This method just discovers if the given filteredKey would work.
      return false;
    }
  }
}
