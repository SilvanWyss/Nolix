//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
/**
 * A statement consists of a specification and can have a next statement.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 */
public final class Statement {
	
	//attribute
	private StandardSpecification specification = new StandardSpecification();
	
	//optional attribute
	private Statement nextStatement;
	
	//constructor
	/**
	 * Creates new statement.
	 */
	public Statement() {}
	
	//constructor
	/**
	 * Creates new statement the given string represents
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
		
		statement.specification = specification;
		
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
	 * @return a string representation of the one attribute of this statement.
	 * @throws InvalidStateException if this statement contains no or several attributes.
	 */
	public String getOneAttributeToString() {
		return specification.getOneAttributeToString();
	}
	
	//method
	/**
	 * @return the attributes of this statement.
	 */
	public AccessorContainer<StandardSpecification> getRefAttributes() {
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
	public StandardSpecification getRefOneAttribute() {
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
	 * @return a reproducing string representation of this statement.
	 */
	public String toReproducingString() {
		
		String string = specification.toReproducingString();
		
		if (hasNextStatement()) {
			string += CharacterCatalogue.DOT + getRefNextStatement().toReproducingString();
		}
		
		return string;
	}
	
	//method
	/**
	 * @return a string representation of this statement.
	 */
	public String toString() {
		
		String string = specification.toReproducingString();
		
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
			
			if (character == CharacterCatalogue.OPENING_BRACKET) {
				openBrackets++;
			}
			else if (character == CharacterCatalogue.CLOSING_BRACKET) {
				openBrackets--;
			}
			else if (character == CharacterCatalogue.DOT && openBrackets == 0) {
				setValue(value.substring(0, i));
				nextStatement = new Statement(value.substring(i + 1, value.length()));
				return;
			}
		}
		
		specification = new StandardSpecification(value);
	}
}
