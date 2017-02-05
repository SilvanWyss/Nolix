/*
 * file:	CursorIcon.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	50
 */

//package declaration
package ch.nolix.element.dialog;

//java import
import java.awt.Cursor;

//own import
import ch.nolix.common.specification.Specification;

//enum
public enum CursorIcon {
	Arrow,
	Cross,
	Edit,
	Hand,
	Move,
	Wait;
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "CursorIcon";
	
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
	 * @return the specification of this cursor icon
	 */
	public final Specification getSpecification() {
		return new Specification(SIMPLE_CLASS_NAME, toString());
	}
}