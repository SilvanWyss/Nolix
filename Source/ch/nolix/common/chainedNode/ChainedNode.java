//package declaration
package ch.nolix.common.chainedNode;

import ch.nolix.common.attributeAPI.Headered;
import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ChainedNode} consists of a {@link Node} and optionally a next {@link ChainedNode}.
 * A {@link ChainedNode} is not mutable as long as its document node or next statement are not mutated.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 280
 */
public final class ChainedNode implements Headered {
	
	//static method
	/**
	 * @param string
	 * @return a new {@link ChainedNode} the given string represents.
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	public static ChainedNode fromString(final String string) {
		return new ChainedNode(string);
	}
	
	//attribute
	private final Node node;
	
	//optional attribute
	private final ChainedNode nextNode;
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode}.
	 */
	public ChainedNode() {
		node = new Node();
		nextNode = null;
	}
		
	//constructor
	/**
	 * Creates a new {@link ChainedNode} that consists of the given document node.
	 * 
	 * @param node
	 * @throws NullArgumentException if the given document node is null.
	 */
	public ChainedNode(final Node node) {
		
		//Checks if the given document node is not null.
		Validator
		.suppose(node)
		.thatIsNamed("document node")
		.isNotNull();
		
		this.node = node.getCopy();
		nextNode = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} that consists of the given document node and next statement.
	 * 
	 * @param node
	 * @param nextStatement
	 * @throws NullArgumentException if the given document node is null.
	 * @throws NullArgumentException if the given next statement is null.
	 */
	public ChainedNode(final Node node, final ChainedNode nextStatement) {
		
		//Checks if the given document node is not null.
		Validator
		.suppose(node)
		.thatIsNamed("document node")
		.isNotNull();
		
		//Checks if the given next statement is not null.
		Validator
		.suppose(nextStatement)
		.thatIsNamed("next statement")
		.isNotNull();
		
		this.node = node;
		this.nextNode = nextStatement;
	}
	
	//constructor
	/**
	 * Creates a new {@link ChainedNode} the given string represents
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	private ChainedNode(final String string) {
		
		//Iterates the given string.
		var openBrackets = 0;
		for (var i = 0; i < string.length() - 1; i++) {
			
			//Enumerates the current character.
			switch (string.charAt(i)) {
				case CharacterCatalogue.OPEN_BRACKET:
					openBrackets++;
					break;
				case CharacterCatalogue.CLOSED_BRACKET:
					openBrackets--;
					break;
				case CharacterCatalogue.DOT:
					if (openBrackets == 0) {
						node = Node.fromString(string.substring(0, i));
						nextNode = new ChainedNode(string.substring(i + 1, string.length()));
						return;
					}
				default:
			}
		}
		
		node = Node.fromString(string);
		nextNode = null;
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} contains attributes.
	 */
	public boolean containsAttributes() {
		return node.containsAttributes();
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link ChainedNode}.
	 */
	public int getAttributeCount() {
		return node.getAttributeCount();
	}
	
	//method
	/**
	 * @return the string representations of the attributes of the current {@link ChainedNode}.
	 */
	public List<String> getAttributesToStrings() {
		return node.getAttributesToStrings();
	}
	
	//method
	/**
	 * @return a copy of the current {@link ChainedNode}.
	 */
	public ChainedNode getCopy() {
		
		//Handles the case that the current statement does not have a next statement.
		if (!hasNextNode()) {
			return new ChainedNode(node);
		}
		
		//Handles the case that the current statement has a next statement.
		return new ChainedNode(node, getRefNextNode());
	}
	
	//method
	/**
	 * @return the header of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a header.
	 */
	@Override
	public String getHeader() {
		
		//Checks if the current statement has a header.
		//This pre-check provides a more suitable error message than the common checks.
		if (!hasHeader()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.HEADER);
		}
		
		return node.getHeader();
	}
	
	//method
	/**
	 * @return a string representation of the next {@link ChainedNode} of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode} does not have a next {@link ChainedNode}.
	 */
	public String getNextNodeAsString() {
		return getRefNextNode().toString();
	}
	
	//method
	/**
	 * @return the integer the one attribute of the current {@link ChainedNode} represents.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link ChainedNode} does not represent an integer.
	 */
	public int getOneAttributeAsInt() {
		return node.getOneAttributeAsInt();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public String getOneAttributeAsString() {
		return node.getOneAttributeAsString();
	}
	
	//method
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link ChainedNode}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentMissesAttributeException if the current {@link ChainedNode}
	 * does not contain an attribute at the given index.
	 */
	public Node getRefAttributeAt(final int index) {
		return node.getRefAttributeAt(index);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link ChainedNode}.
	 */
	public IContainer<Node> getRefAttributes() {
		return node.getRefAttributes();
	}
	
	//method
	/**
	 * @return the next {@link ChainedNode} of the current {@link ChainedNode}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link ChainedNode} does not have a next {@link ChainedNode}.
	 */
	public ChainedNode getRefNextNode() {
		
		//Checks if the current statement has a next statement.
		if (!hasNextNode()) {
			throw new ArgumentMissesAttributeException(this, "next statement");
		}
		
		return nextNode;
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link ChainedNode}.
	 * @throws EmptyArgumentException if the current {@link ChainedNode} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link ChainedNode} contains several attributes.
	 */
	public Node getRefOneAttribute() {
		return node.getRefOneAttribute();
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} has a header.
	 */
	public boolean hasHeader() {
		return node.hasHeader();
	}
	
	//method
	/**
	 * @return true if the current {@link ChainedNode} has a next {@link ChainedNode}.
	 */
	public boolean hasNextNode() {
		return (nextNode != null);
	}
	
	//method
	/**
	 * @return a string representation of the current {@link ChainedNode}.
	 */
	@Override
	public String toString() {
		
		var string = node.toString();
		
		//Handles the case that the current statement} has a next statement.
		if (hasNextNode()) {
			string += CharacterCatalogue.DOT + getRefNextNode().toString();
		}
		
		return string;
	}
}
