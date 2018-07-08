//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Cursor;

//own import
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 50
 */
public enum CursorIcon implements ISpecifiedEnum {
	Arrow,
	Cross,
	Edit,
	Hand,
	Move,
	Wait;
	
	//constant
	public static final String TYPE_NAME = "CursorIcon";
	
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
