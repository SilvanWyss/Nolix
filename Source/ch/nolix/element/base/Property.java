//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;

//class
public abstract class Property {
	
	//visibility-reduced constructor
	Property() {}
	
	//visibility-reduced method declaration
	abstract void addOrChangeAttribute(BaseNode specification);
	
	//visibility-reduced method declaration
	abstract void fillUpAttributesInto(LinkedList<Node> list);
	
	//visibility-reduced method declaration
	abstract String getCode();
	
	//visibility-reduced method
	final boolean hasCode(final String code) {
		return getCode().equals(code);
	}
}
