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
	protected abstract boolean acceptsAttribute(BaseNode attribute);
	
	//method
	protected final boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (acceptsAttribute(attribute)) {
			addOrChangeAttribute(attribute);
			return true;
		}
		
		return false;
	}
	
	//method declaration
	protected abstract void addOrChangeAttribute(BaseNode attribute);
	
	//method declaration
	protected abstract void fillUpAttributesInto(LinkedList<Node> list);
}
