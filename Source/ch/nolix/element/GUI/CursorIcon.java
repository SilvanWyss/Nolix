//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Cursor;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 * @lines 60
 */
public enum CursorIcon implements IElementEnum {
	ARROW,
	CROSS,
	EDIT,
	HAND,
	MOVE,
	WAIT;
	
	//constant
	public static final String TYPE_NAME = "CursorIcon";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link CursorIcon} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static CursorIcon fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader().toUpperCase());
	}
	
	//method
	/**
	 * @return a {@link Cursor} representation of the current {@link CursorIcon}.
	 */
	public Cursor toCursor() {
		
		//Enumerates the current cursor icon.
		switch (this) {
			case ARROW:
				return new Cursor(Cursor.DEFAULT_CURSOR);
			case CROSS:
				return new Cursor(Cursor.CROSSHAIR_CURSOR);
			case EDIT:
				return new Cursor(Cursor.TEXT_CURSOR);
			case HAND:
				return new Cursor(Cursor.HAND_CURSOR);
			case MOVE:
				return new Cursor(Cursor.MOVE_CURSOR);
			case WAIT:
				return new Cursor(Cursor.WAIT_CURSOR);
			default:
				return new Cursor(Cursor.DEFAULT_CURSOR);
		}
	}
}
