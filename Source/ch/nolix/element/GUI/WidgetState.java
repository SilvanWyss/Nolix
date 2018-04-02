//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
/**
 * A {@link WidgetState} specifies the state of a {@link Widget}.
 * A {@link WidgetState} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 30
 */
public enum WidgetState implements ISpecifiedEnum {
	Normal,
	Hovered,
	Focused,
	HoverFocused,
	Disabled,
	Collapsed;
	
	//constant
	public static final String TYPE_NAME = "WidgetState";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link WidgetState} from the given specification.
	 */
	public static WidgetState createFromSpecification(final Specification specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
