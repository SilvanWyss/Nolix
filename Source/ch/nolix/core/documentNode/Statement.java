//package declaration
package ch.nolix.core.documentNode;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
/**
 * A statement consists of a specification and can have a next statement.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 230
 */
public final class Statement {
	
	//attribute
	private DocumentNode specification = new DocumentNode();
	
	//optional attribute
	private Statement nextStatement;
	
	//constructor
	/**
	 * Creates a new statement.
	 */
	public Statement() {}
	
	//constructor
	/**
	 * Creates a new statement the given string represents
	 * 
	 * @param string
	 * @throws InvalidArgumentException if the given string is not valid.
	 */
	public Statement(final String string) {
		setValue(string);
	}
	
	public Statement(String leftMouseButtonPressCommand, String[] arguments) {
		
		specification.setHeader(leftMouseButtonPressCommand);
		
		for (final String a : arguments) {
			specification.addAttribute(a);
		}
	}

	//method
	/**
	 * @return true if this statement contains attributes.
	 */
	public boolean containsAttributes() {
		return specification.containsAttributes();
	}
	
	//method
	/**
	 * @return the number of attributes of this statement.
	 */
	public int getAttributeCount() {
		return specification.getAttributeCount();
	}
	
	//method
	/**
	 * @return the string representations of the attributes of this statement.
	 */
	public List<String> getAttributesToStrings() {
		return specification.getAttributesToStrings();
	}
	
	//method
	/**
	 * @return a copy of this statement.
	 */
	public Statement getCopy() {
		
		final Statement statement = new Statement();
		
		statement.specification = specification.createCopy();
		
		if (hasNextStatement()) {
			statement.nextStatement = getRefNextStatement().getCopy();
		}
		
		return statement;
	}
	
	//method
	/**
	 * @return the first part of this statement to string.
	 */
	public String getFirstPartToString() {
		return specification.toString();
	}
	
	//method
	/**
	 * @return the header of this statement.
	 * @throws UnexistingAttributeException if this statement has no header.
	 */
	public String getHeader() {
		return specification.getHeader();
	}
	
	//method
	/**
	 * @return a string representation of the next statement of this statement.
	 * @throws UnexistingAttributeException if this statement has no next statement.
	 */
	public String getNextStatementToString() {
		return getRefNextStatement().toString();
	}
	
	//method
	/**
	 * @return the integer the one attribute of this statement represents.
	 * @throws InvalidStateException if this statement contains no or several attributes.
	 * @throws InvalidArgumentException if the one attribute of this statement represents no integer.
	 */
	public int getOneAttributeAsInt() {
		return specification.getOneAttributeAsInt();
	}
	
	//method
	/**
	 * @return a string representation of the one attribute of this statement.
	 * @throws InvalidStateException if this statement contains no or several attributes.
	 */
	public String getOneAttributeToString() {
		return specification.getOneAttributeAsString();
	}
	
	//method
	/**
	 * @return the attributes of this statement.
	 */
	public IContainer<DocumentNode> getRefAttributes() {
		return specification.getRefAttributes();
	}
	
	//method
	/**
	 * @return the next statement of this statement.
	 * @throws UnexistingAttributeException if this statement has no next statement.
	 */
	public Statement getRefNextStatement() {
		
		//Checks if this statement has a next statement.
		if (!hasNextStatement()) {
			throw new UnexistingAttributeException(this, "next statement");
		}
		
		return nextStatement;
	}
	
	//method
	/**
	 * @return the one attribute of this statement.
	 * @throws InvalidStateException if this statement contains no or several attributes.
	 */
	public DocumentNode getRefOneAttribute() {
		return specification.getRefOneAttribute();
	}
	
	//method
	/**
	 * @return true if this statement has a header.
	 */
	public boolean hasHeader() {
		return specification.hasHeader();
	}
	
	//method
	/**
	 * @return true if this statement has a next statement.
	 */
	public boolean hasNextStatement() {
		return (nextStatement != null);
	}
	
	//method
	/**
	 * @return a string representation of this statement.
	 */
	public String toString() {
		
		String string = specification.toString();
		
		//Handles the case that the current statement has a next statement.
		if (hasNextStatement()) {
			string += CharacterCatalogue.DOT + getRefNextStatement().toString();
		}
				
		return string;
	}
	
	//method
	/**
	 * Sets the value of this statement.
	 * 
	 * @param value
	 * @throws InvalidArgumentException if the given value is not valid.
	 */
	private void setValue(final String value) {
		
		int openBrackets = 0;
		
		for (int i = 0; i < value.length() - 1; i++) {
			
			final char character = value.charAt(i);
			
			if (character == CharacterCatalogue.OPEN_BRACKET) {
				openBrackets++;
			}
			else if (character == CharacterCatalogue.CLOSED_BRACKET) {
				openBrackets--;
			}
			else if (character == CharacterCatalogue.DOT && openBrackets == 0) {
				setValue(value.substring(0, i));
				nextStatement = new Statement(value.substring(i + 1, value.length()));
				return;
			}
		}
		
		specification = new DocumentNode(value);
	}
}
