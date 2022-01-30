//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
public enum DatabaseState implements IElement<DatabaseState> {
	UNINITIALIZED,
	READY,
	LOCKED;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
