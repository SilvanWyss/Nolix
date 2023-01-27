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
	default String getQualifiedName() {
		return (getNamePrefix() + getName());
	}
	
	//method
	default String getQualifiedNameInQuotes() {
		return ("'" + getQualifiedName() + "'");
	}
	
	//method declaration
	String getNamePrefix();
}
