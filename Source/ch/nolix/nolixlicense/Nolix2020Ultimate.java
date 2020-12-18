//package declaration
package ch.nolix.nolixlicense;

//own import
import ch.nolix.common.license.License;

//class
public final class Nolix2020Ultimate extends License {
	
	//method
	//key = '48956380'
	@Override
	protected boolean accepts(final String key) {
		
		final var keyAsInt = Integer.valueOf(key);
		
		return (keyAsInt / 20 - keyAsInt % 260 - 9 * keyAsInt + 438_159_801 == 0);
	}
}
