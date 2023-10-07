//package declaration
package ch.nolix.system.element.property;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public abstract class Property {
	
	//constructor
	protected Property() {}
	
	//method declaration
	public abstract boolean addedOrChangedAttribute(final INode<?> attribute);
	
	//method declaration
	public abstract void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
