//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 30
 */
public enum CaptionPosition implements IElementEnum {
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
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
