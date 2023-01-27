//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Labeled;

//interface
/**
 * A {@link FluentLabelable} is a {@link Labeled} whose label can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-08-26
 * @param <L> is the type of a {@link FluentLabelable}.
 */
public interface FluentLabelable<L extends FluentLabelable<L>> extends Labeled {
	
	//method declaration
	/**
	 * Sets the label of the current {@link FluentLabelable}.
	 * 
	 * @param label
	 * @return the current {@link FluentLabelable}.
	 */
	L setLabel(String label);
}
