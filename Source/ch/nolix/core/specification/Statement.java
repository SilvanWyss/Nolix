/*
 * file:	Statement.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	120
 */

//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;

//class
public final class Statement extends Specificationoid {
	
	//optional attribute
	Statement nextStatement;
	
	//constructor
	/**
	 * Creates new statement without header, without attributes and without next statement.
	 */
	public Statement() {}
	
	//constructor
	/**
	 * Creates new statement the given string represents
	 * 
	 * @param string
	 * @throws Exception if the given string is not valid
	 */
	public Statement(String string) {
		setValue(string);
	}
	
	public Statement getCopy() {
		//TODO: Implement better.
		return new Statement(toString());
	}
	
	//method
	/**
	 * @return the first part of this statement to string
	 */
	public String getFirstPartToString() {
		
		//Calls method of the base class.
		return super.toString();
	}
	
	//method
	/**
	 * @return the next statement of this statement
	 * @throws UnexistingAttributeException if this statement has no next statement
	 */
	public Statement getNextStatement() {
		
		if (!hasNextStatement()) {
			throw new UnexistingAttributeException(this, "next statement");
		}
		
		return nextStatement;
	}
	
	//method
	/**
	 * @return a string representation of the next statement of this statement
	 * @throws Exception if this statement has no next statement
	 */
	public String getNextStatementToString() {
		return getNextStatement().toString();
	}
	
	//method
	/**
	 * @return true if this statement has a next statement
	 */
	public boolean hasNextStatement() {
		return (nextStatement != null);
	}
	
	//method
	/**
	 * Sets the value of this statement.
	 * 
	 * @param value
	 * @throws Exception if the given value is not valid
	 */
	public void setValue(String value) {
		
		int openBrackets = 0;
		for (int i = 0; i < value.length() - 1; i++) {
			char character = value.charAt(i);
			if (character == CharacterManager.OPENING_BRACKET) {
				openBrackets++;
			}
			else if (character == CharacterManager.CLOSING_BRACKET) {
				openBrackets--;
			}
			else if (character == CharacterManager.DOT && openBrackets == 0) {
				setValue(value.substring(0, i));
				nextStatement = new Statement(value.substring(i + 1, value.length()));
				return;
			}
		}
		
		//Calls method of the base class.
		super.setValue(value);
	}
	
	//method
	/**
	 * @return a string representation of this statement
	 */
	public final String toReproducingString() {
		
		//Calls method of the base class.
		String string = super.toReproducingString();
		
		if (hasNextStatement()) {
			string += "." + getNextStatement().toReproducingString();
		}
		
		return string;
	}
	
	public final String toString() {
		
		//Calls method of the base class.
		String string = super.toReproducingString();
		
		if (hasNextStatement()) {
			string += "." + getNextStatement().toString();
		}
				
		return string;
	}
}
