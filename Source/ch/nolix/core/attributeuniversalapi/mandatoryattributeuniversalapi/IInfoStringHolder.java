//package declaration
package ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi;

//own imports
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IInfoStringHolder} has an info string.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IInfoStringHolder {
	
	//method declaration
	/**
	 * @return the info string of the current {@link IInfoStringHolder}.
	 */
	String getInfoString();
	
	//method
	/**
	 * @param infoString
	 * @return true if the current {@link IInfoStringHolder} has the given info string.
	 */
	default boolean hasInfoString(final String infoString) {
		return getInfoString().equals(infoString);
	}
}
