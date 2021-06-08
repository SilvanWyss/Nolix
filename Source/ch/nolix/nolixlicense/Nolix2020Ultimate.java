//package declaration
package ch.nolix.nolixlicense;

//own imports
import ch.nolix.common.license.License;

//class
public final class Nolix2020Ultimate extends License {
	
	//method
	//filteredKey = '48956380'
	@Override
	protected boolean acceptsFilteredKey(final String filteredKey) {
		try {
			final var keyAsInt = Integer.valueOf(filteredKey);
			return (keyAsInt / 20 - keyAsInt % 260 - 9 * keyAsInt + 438_159_801 == 0);
		} catch (final Exception exception) {
			return false;
		}
	}
}
