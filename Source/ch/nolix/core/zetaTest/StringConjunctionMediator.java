//package declaration
package ch.nolix.core.zetaTest;

//class
/**
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 30
 * 
 * A string conjunction mediator supports additional expectations for the value of a string mediator.
 * A string conjunction mediator supports only the expectations that are meaningful to be supported as additional expectations.
 */
public final class StringConjunctionMediator {

	//attribute
	private final ZetaTest zetaTest;
	private final String value;
	
	//package-visible constructor
	/**
	 * Creates new string conjunction mediator with the given value.
	 * 
	 * @param value
	 */
	StringConjunctionMediator(final ZetaTest test, final String value) {
		this.zetaTest = test;
		this.value = value;
	}
	
	//method
	public StringConjunctionMediator andContains(final char character) {
		
		new StringMediator(zetaTest, value).contains(character);
		
		return this;
	}
}
