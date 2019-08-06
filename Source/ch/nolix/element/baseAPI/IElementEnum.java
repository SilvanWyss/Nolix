//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public interface IElementEnum extends IElement {
	
	//default method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public default List<DocumentNode> getAttributes() {
		return DocumentNode.createWithHeader(toString()).intoList();
	}
}
