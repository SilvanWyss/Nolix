//package declaration
package ch.nolix.nolixlicense;

//own imports
import ch.nolix.common.license.License;

//class
public final class Nolix2020Classic extends License {
	
	//method
	//key = '45680060'
	@Override
	protected boolean accepts(final String key) {
		try {
			final var keyAsInt = Integer.valueOf(key);
			return (-3 * keyAsInt + keyAsInt / 20 - keyAsInt % 250 + 134_756_237 == 0);
		}
		catch (final Exception exception) {
			return false;
		}
	}
}
