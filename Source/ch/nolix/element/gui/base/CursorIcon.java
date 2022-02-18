//package declaration
package ch.nolix.element.gui.base;

//Java imports
import java.awt.Cursor;
import java.util.Locale;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public enum CursorIcon implements IElement<CursorIcon> {
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
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static CursorIcon fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader().toUpperCase(Locale.ENGLISH));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
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
