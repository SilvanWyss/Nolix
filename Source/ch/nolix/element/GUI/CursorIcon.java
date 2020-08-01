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
 * @month 2016-05
 * @lines 60
 */
public enum CursorIcon implements IElementEnum {
	Arrow,
	Cross,
	Edit,
	Hand,
	Move,
	Wait;
	
	//constant
	public static final String TYPE_NAME = "CursorIcon";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link CursorIcon} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static CursorIcon fromSpecification(
		final BaseNode specification
	) {
		return valueOf(specification.getOneAttributeHeader());
	}
	
	//method
	/**
	 * @return the Java cursor of the current {@link CursorIcon}.
	 */
	public Cursor getJavaCursor() {
		
		//Enumerates the current cursor icon.
		switch (this) {
			case Arrow:
				return new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
			case Cross:
				return new Cursor(java.awt.Cursor.CROSSHAIR_CURSOR);
			case Edit:
				return new Cursor(java.awt.Cursor.TEXT_CURSOR);
			case Hand:
				return new Cursor(java.awt.Cursor.HAND_CURSOR);
			case Move:
				return new Cursor(java.awt.Cursor.MOVE_CURSOR);
			case Wait:
				return new Cursor(java.awt.Cursor.WAIT_CURSOR);
			default:
				return new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
		}
	}
}
