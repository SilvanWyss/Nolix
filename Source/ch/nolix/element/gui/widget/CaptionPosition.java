//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-05-01
 */
public enum CaptionPosition implements IElement<CaptionPosition> {
	LEFT_TOP,
	LEFT,
	LEFT_BOTTOM,
	RIGHT_TOP,
	RIGHT,
	RIGHT_BOTTOM,
	TOP_LEFT,
	TOP,
	TOP_RIGHT,
	BOTTOM_LEFT,
	BOTTOM,
	BOTTOM_RIGHT;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
