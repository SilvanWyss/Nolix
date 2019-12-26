//package declaration
package ch.nolix.nolixLicenses;

//own import
import ch.nolix.common.license.License;

//class
public final class Nolix2020Classic extends License {
	
	//constructor
	public Nolix2020Classic(final String key) {
		super(key);
	}
	
	//method
	//key = '45680060'
	@Override
	public boolean accepts(final String key) {
		
		final var keyAsInt = Integer.valueOf(key);
		
		return (-3 * keyAsInt + keyAsInt / 20 - keyAsInt % 250 + 134756237 == 0);
	}
}
