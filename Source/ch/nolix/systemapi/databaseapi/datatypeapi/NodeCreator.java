//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.core.document.node.Node;

//class
final class NodeCreator implements IValueCreator<Node> {
	
	//method
	@Override
	public Node createValueFromString(final String string) {
		return Node.fromString(string);
	}
}
