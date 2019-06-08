//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Signed} has a signature.
 * 
 * A signature is a string representation of an object,
 * that can identify the object, but is mostly shorter than the common string representation.
 * 
 * Example:
 * There is given a person (surname: Peter, name: Muster, date of birth: 01.01.2000, hair color: brown, hobby: fishing).
 * -string representation: "Peter, Muster, 2000-01-01, brown, fishing"
 * -signature: "Peter Muster, 2001-01-01"
 * 
 * Example:
 * There is given a package (index: 25000, content: HelloWorld).
 * -string representation: "25000, HelloWorld"
 * -signature: "25000"
 * 
 * @author Silvan Wyss
 * @month 2018-11
 * @lines 50
 */
public interface Signed {
	
	//abstract method
	/**
	 * @return the signature of the current {@link Signed}.
	 */
	public abstract String getSignature();
	
	//default method
	/**
	 * @return the signature of the current {@link Signed} in brackets.
	 */
	public default String getSignatureInBrackets() {
		return ("(" + getSignature() + ")");
	}
	
	//default method
	/**
	 * @return the signature of the current {@link Signed} in quotes.
	 */
	public default String getSignatureInQuotes() {
		return ("'" + getSignature() + "'");
	}
	
	//default method
	/**
	 * @return true if he current {@link Signed} has the given signature.
	 */
	public default boolean hasSignature(final String signature) {
		return getSignature().equals(signature);
	}
}
