//package declaration
package ch.nolix.element.elementAPI;

//own imports
import ch.nolix.common.commonTypeHelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @date 2018-03-04
 * @lines 20
 */
public interface IElementEnum extends IElement {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default LinkedList<Node> getAttributes() {
		return new Node(StringHelper.toPascalCase(toString())).intoList();
	}
}
