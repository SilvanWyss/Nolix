//package declaration
package ch.nolix.system.database.databaseschemaadapter;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum DatabaseState implements IElement {
	UNINITIALIZED,
	READY,
	LOCKED;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(StringHelper.toPascalCase(toString())));
	}
}
