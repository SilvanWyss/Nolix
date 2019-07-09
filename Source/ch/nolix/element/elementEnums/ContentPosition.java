//package declaration
package ch.nolix.element.elementEnums;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public enum ContentPosition implements IElementEnum {
	LeftTop,
	Left,
	LeftBottom,
	Top,
	Center,
	Bottom,
	RightTop,
	Right,
	RightBottom;
	
	//constant
	public static final String TYPE_NAME = "ContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ContentPosition createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
