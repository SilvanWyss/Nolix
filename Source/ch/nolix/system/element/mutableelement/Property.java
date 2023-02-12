//package declaration
package ch.nolix.system.element.mutableelement;

import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public abstract class Property {
	
	//constructor
	protected Property() {}
	
	//method declaration
	protected abstract boolean addedOrChangedAttribute(final INode<?> attribute);
	
	//method declaration
	protected abstract void fillUpAttributesInto(ILinkedList<INode<?>> list);
}
