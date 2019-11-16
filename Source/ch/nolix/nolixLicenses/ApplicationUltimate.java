//package declaration
package ch.nolix.nolixLicenses;

//own import
import ch.nolix.common.license.License;

//class
public final class ApplicationUltimate extends License {
	
	//constructor
	public ApplicationUltimate(final String key) {
		super(key);
	}
	
	//method
	//key = '45680060'
	@Override
	public boolean accepts(final String key) {
		
		final var keyAsInt = Integer.valueOf(key);
		
		return (keyAsInt / 20 - keyAsInt % 250 == 3 * keyAsInt - 134756237);
	}
}
