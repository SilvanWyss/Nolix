//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.SemanticVersioned;

//interface
/**
 * A {@link SemanticVersionable} is a {@link SemanticVersioned} whose semantic version can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-30
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
	 * @throws RuntimeException if the given version is not valid.
	 */
	SV setVersion(String version);
}
