//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
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
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static WidgetState fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
