//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ISelectingStyle} is a {@link IBaseStyle} that
 * can select or skip a {@link IStylableElement} that is given to be styled.
 *  
 * @author Silvan Wyss
 * @date 2023-07-08
 */
@AllowDefaultMethodsAsDesignPattern
public interface ISelectingStyle extends IBaseStyle {
	
	//method declaration
	/**
	 * @param element
	 * @return true if the current {@link ISelectingStyle} would select the given element to style.
	 */
	boolean selectsElement(IStylableElement<?> element);
	
	//method
	/**
	 * @param element
	 * @return true if the current {@link ISelectingStyle} would not (!) select the given element to style.
	 */
	default boolean skipsElement(final IStylableElement<?> element) {
		return !selectsElement(element);
	}
}
