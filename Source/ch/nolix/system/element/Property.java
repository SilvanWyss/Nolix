//package declaration
package ch.nolix.system.element;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public abstract class Property {
	
	//constructor
	protected Property() {}
	
	//method declaration
	protected abstract boolean addedOrChangedAttribute(final INode<?> attribute);
	
	//method declaration
	protected abstract void fillUpAttributesInto(LinkedList<INode<?>> list);
}
