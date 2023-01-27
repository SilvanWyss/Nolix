//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link QualifiedNamed} is a {@link Named} that has a name prefix that completes its name to a qualified name.
 * 
 * @author Silvan Wyss
 * @date 2022-01-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface QualifiedNamed extends Named {
	
	//method
	/**
	 * @return the qualified name of the current {@link QualifiedNamed}.
	 */
	default String getQualifiedName() {
		return (getNamePrefix() + getName());
	}
	
	//method
	/**
	 * @return the qualified name of the current {@link QualifiedNamed} in quotes.
	 */
	default String getQualifiedNameInQuotes() {
		return ("'" + getQualifiedName() + "'");
	}
	
	//method declaration
	/**
	 * @return the name prefix of the current {@link QualifiedNamed}.
	 * The name prefix of a {@link QualifiedNamed} completes
	 * the name of the {@link QualifiedNamed} to a qualified name.
	 */
	String getNamePrefix();
}
