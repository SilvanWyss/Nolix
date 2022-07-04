//package declaration
package ch.nolix.system.element;

import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public abstract class Property {
	
	//constructor
	protected Property() {}
	
	//method declaration
	protected abstract boolean addedOrChangedAttribute(final INode<?> attribute);
	
	//method declaration
	protected abstract void fillUpAttributesInto(IMutableList<INode<?>> list);
}
