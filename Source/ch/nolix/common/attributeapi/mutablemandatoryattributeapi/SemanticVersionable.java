//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.common.attributeapi.mandatoryattributeapi.SemanticVersioned;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//interface
/**
* A {@link SemanticVersionable} is a {@link SemanticVersioned} whose semantic version can be set programmatically.
* 
* @author Silvan Wyss
* @month 2020-03
* @lines 50
* @param <SV> is the type of a {@link SemanticVersionable}.
*/
public interface SemanticVersionable<SV extends SemanticVersionable<SV>> extends SemanticVersioned {
	
	//method declaration
	/**
	 * Sets the version of the current {@link SemanticVersionable}.
	 * 
	 * @param majorVersion
	 * @param minorVersion
	 * @param patchVersion
	 * @return the current {@link SemanticVersionable}.
	 */
	SV setVersion(final int majorVersion, final int minorVersion, final int patchVersion);
	
	//method declaration
	/**
	 * Sets the version of the current {@link SemanticVersionable}.
	 * 
	 * @param version
	 * @return the current {@link SemanticVersionable}.
	 * @throws ArgumentIsNullException if the given version is null.
	 * @throws InvalidArgumentException if the given version is blank.
	 */
	default SV setVersion(final String version) {
		
		Validator.assertThat(version).thatIsNamed(LowerCaseCatalogue.VERSION).isNotBlank();
		
		final var array = version.split(".");
		
		Validator.assertThat(array).hasElementCount(3);
		
		return setVersion(Integer.valueOf(array[0]), Integer.valueOf(array[1]), Integer.valueOf(array[2]));
	}
}
