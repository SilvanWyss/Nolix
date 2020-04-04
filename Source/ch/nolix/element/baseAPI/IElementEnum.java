//package declaration
package ch.nolix.element.baseAPI;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public interface IElementEnum extends IElement {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public default LinkedList<Node> getAttributes() {
		return new Node(toString()).intoList();
	}
}
