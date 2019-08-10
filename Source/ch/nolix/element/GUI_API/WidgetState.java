//package declaration
package ch.nolix.element.GUI_API;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * A {@link WidgetState} specifies the state of a {@link Widget}.
 * A {@link WidgetState} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 30
 */
public enum WidgetState implements IElementEnum {
	Normal,
	Hovered,
	Focused,
	Disabled,
	Collapsed;
	
	//constant
	public static final String TYPE_NAME = "WidgetState";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link WidgetState} from the given specification.
	 */
	public static WidgetState createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
