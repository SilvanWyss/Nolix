//package declaration
package ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi;

//own imports
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link FullNamed} is a {@link Named} that has
 * a name prefix the name of a {@link FullNamed} can be concatenated to a full name.
 * 
 * @author Silvan Wyss
 * @date 2022-01-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface FullNamed extends Named {
	
	//method
	default String getFullName() {
		return (getNamePrefix() + getName());
	}
	
	//method
	default String getFullNameInQuotes() {
		return ("'" + getFullName() + "'");
	}
	
	//method declaration
	String getNamePrefix();
}
