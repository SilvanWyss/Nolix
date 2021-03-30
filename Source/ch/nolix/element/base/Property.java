//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;

//class
public abstract class Property {
	
	//constructor
	Property() {}
	
	//method declaration
	abstract void addOrChangeAttribute(BaseNode specification);
	
	//method declaration
	abstract void fillUpAttributesInto(LinkedList<Node> list);
	
	//method declaration
	abstract String getCode();
	
	//method
	final boolean hasCode(final String code) {
		return getCode().equals(code);
	}
}
