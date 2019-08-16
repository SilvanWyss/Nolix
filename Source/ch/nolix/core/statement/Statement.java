//package declaration
package ch.nolix.core.statement;

//own imports
import ch.nolix.core.attributeAPI.Headered;
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.ArgumentMissesAttributeException;
import ch.nolix.core.node.Node;
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link Statement} consists of a {@link Node} and optionally a next {@link Statement}.
 * A {@link Statement} is not mutable as long as its document node or next statement are not mutated.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 280
 */
public final class Statement implements Headered {
	
	//static method
	/**
	 * @param string
	 * @return a new {@link Statement} the given string represents.
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	public static Statement fromString(final String string) {
		return new Statement(string);
	}
	
	//attribute
	private final Node node;
	
	//optional attribute
	private final Statement nextStatement;
	
	//constructor
	/**
	 * Creates a new {@link Statement}.
	 */
	public Statement() {
		node = new Node();
		nextStatement = null;
	}
		
	//constructor
	/**
	 * Creates a new {@link Statement} that consists of the given document node.
	 * 
	 * @param node
	 * @throws NullArgumentException if the given document node is null.
	 */
	public Statement(final Node node) {
		
		//Checks if the given document node is not null.
		Validator
		.suppose(node)
		.thatIsNamed("document node")
		.isNotNull();
		
		this.node = node.getCopy();
		nextStatement = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link Statement} that consists of the given document node and next statement.
	 * 
	 * @param node
	 * @param nextStatement
	 * @throws NullArgumentException if the given document node is null.
	 * @throws NullArgumentException if the given next statement is null.
	 */
	public Statement(final Node node, final Statement nextStatement) {
		
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
		this.nextStatement = nextStatement;
	}
	
	//constructor
	/**
	 * Creates a new {@link Statement} the given string represents
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	private Statement(final String string) {
		
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
						node = Node.createFromString(string.substring(0, i));
						nextStatement = new Statement(string.substring(i + 1, string.length()));
						return;
					}
				default:
			}
		}
		
		node = Node.createFromString(string);
		nextStatement = null;
	}
	
	//method
	/**
	 * @return true if the current {@link Statement} contains attributes.
	 */
	public boolean containsAttributes() {
		return node.containsAttributes();
	}
	
	//method
	/**
	 * @return the number of attributes of the current {@link Statement}.
	 */
	public int getAttributeCount() {
		return node.getAttributeCount();
	}
	
	//method
	/**
	 * @return the string representations of the attributes of the current {@link Statement}.
	 */
	public List<String> getAttributesToStrings() {
		return node.getAttributesToStrings();
	}
	
	//method
	/**
	 * @return a copy of the current {@link Statement}.
	 */
	public Statement getCopy() {
		
		//Handles the case that the current statement does not have a next statement.
		if (!hasNextStatement()) {
			return new Statement(node);
		}
		
		//Handles the case that the current statement has a next statement.
		return new Statement(node, getRefNextStatement());
	}
	
	//method
	/**
	 * @return the header of the current {@link Statement}.
	 * @throws ArgumentMissesAttributeException if the current {@link Statement} does not have a header.
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
	 * @return a string representation of the next {@link Statement} of the current {@link Statement}.
	 * @throws ArgumentMissesAttributeException if the current {@link Statement} does not have a next {@link Statement}.
	 */
	public String getNextStatementAsString() {
		return getRefNextStatement().toString();
	}
	
	//method
	/**
	 * @return the integer the one attribute of the current {@link Statement} represents.
	 * @throws EmptyArgumentException if the current {@link Statement} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link Statement} contains several attributes.
	 * @throws InvalidArgumentException
	 * if the one attribute of the current {@link Statement} does not represent an integer.
	 */
	public int getOneAttributeAsInt() {
		return node.getOneAttributeAsInt();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of the current {@link Statement}.
	 * @throws EmptyArgumentException if the current {@link Statement} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link Statement} contains several attributes.
	 */
	public String getOneAttributeAsString() {
		return node.getOneAttributeAsString();
	}
	
	//method
	/**
	 * @param index
	 * @return the attribute at the given index from the current {@link Statement}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentMissesAttributeException if the current {@link Statement}
	 * does not contain an attribute at the given index.
	 */
	public Node getRefAttributeAt(final int index) {
		return node.getRefAttributeAt(index);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Statement}.
	 */
	public IContainer<Node> getRefAttributes() {
		return node.getRefAttributes();
	}
	
	//method
	/**
	 * @return the next {@link Statement} of the current {@link Statement}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link Statement} does not have a next {@link Statement}.
	 */
	public Statement getRefNextStatement() {
		
		//Checks if the current statement has a next statement.
		if (!hasNextStatement()) {
			throw new ArgumentMissesAttributeException(this, "next statement");
		}
		
		return nextStatement;
	}
	
	//method
	/**
	 * @return the one attribute of the current {@link Statement}.
	 * @throws EmptyArgumentException if the current {@link Statement} does not contain an attribute.
	 * @throws InvalidArgumentException if the current {@link Statement} contains several attributes.
	 */
	public Node getRefOneAttribute() {
		return node.getRefOneAttribute();
	}
	
	//method
	/**
	 * @return true if the current {@link Statement} has a header.
	 */
	public boolean hasHeader() {
		return node.hasHeader();
	}
	
	//method
	/**
	 * @return true if the current {@link Statement} has a next {@link Statement}.
	 */
	public boolean hasNextStatement() {
		return (nextStatement != null);
	}
	
	//method
	/**
	 * @return a string representation of the current {@link Statement}.
	 */
	@Override
	public String toString() {
		
		var string = node.toString();
		
		//Handles the case that the current statement} has a next statement.
		if (hasNextStatement()) {
			string += CharacterCatalogue.DOT + getRefNextStatement().toString();
		}
		
		return string;
	}
}
