//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;

//class
public abstract class Property {
	
	//constructor
	protected Property() {}
	
	//method declaration
	protected abstract boolean addedOrChangedAttribute(final BaseNode attribute);
	
	//method declaration
	protected abstract void fillUpAttributesInto(LinkedList<Node> list);
}
