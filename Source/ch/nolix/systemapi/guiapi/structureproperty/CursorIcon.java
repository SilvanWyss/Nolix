//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//Java imports
import java.awt.Cursor;
import java.util.Locale;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.webapi.cssapi.CSSCursorCatalogue;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public enum CursorIcon {
	ARROW,
	CROSS,
	EDIT,
	HAND,
	MOVE,
	WAIT;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link CursorIcon} from the given specification.
	 * @throws RuntimeException if the given specification does not represent a {@link CursorIcon}.
	 */
	public static CursorIcon fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader().toUpperCase(Locale.ENGLISH));
	}
	
	//method
	/**
	 * @return a CSS value representation of the current {@link CursorIcon}.
	 */
	public String toCSSValue() {
		switch (this) {
			case ARROW:
				return CSSCursorCatalogue.DEFAULT;
			case CROSS:
				return CSSCursorCatalogue.CROSSHAIR;
			case EDIT:
				return CSSCursorCatalogue.TEXT;
			case HAND:
				return CSSCursorCatalogue.POINTER;
			case MOVE:
				return CSSCursorCatalogue.MOVE;
			case WAIT:
				return CSSCursorCatalogue.WAIT_CONSTANT;
			default:
				throw InvalidArgumentException.forArgument(this);
		}
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
