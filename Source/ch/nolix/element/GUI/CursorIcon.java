//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Cursor;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 50
 */
public enum CursorIcon implements SpecifiedByClassNameAndOneAttribute {
	Arrow,
	Cross,
	Edit,
	Hand,
	Move,
	Wait;
	
	//type name
	public static final String TYPE_NAME = "CursorIcon";
	
	//method
	/**
	 * @return the java cursor of this cursor icon
	 */
	public Cursor getJavaCursor() {
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

	//method
	/**
	 * @return the attribute of this cursor icon.
	 */
	public StandardSpecification getAttribute() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
}