//package declaration
package ch.nolix.element.data;

//own import
import ch.nolix.element.core.NonEmptyText;

//class
/**
 * A token is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 */
public final class Token extends NonEmptyText {
	
	//type name
	public static final String TYPE_NAME = "Token";

	//constructor
	/**
	 * Creates new token with a default value.
	 */
	public Token() {}
	
	//constructor
	/**
	 * Creates new token with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null.
	 * @throws EmptyArgumentException if the given value is empty.
	 */
	public Token(final String value) {
		
		//Calls constructor of the base class.
		super(value);
	}
}
