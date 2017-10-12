//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A textoid is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public abstract class Textoid extends Element {

	//default value
	private static final String DEFAULT_VALUE = StringCatalogue.EMPTY_STRING;
	
	//attribute
	private final String value;
	
	//constructor
	/**
	 * Creates a new textoid with a default value.
	 */
	public Textoid() {
		
		//Calls other constructor
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new textoid with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is null
	 */
	public Textoid(final String value) {
		
		//Checks if the given value is not null.
		Validator.suppose(value).thatIsNamed("value").isNotNull();
		
		//Sets the value of this textoid.
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this textoid.
	 */
	public final List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			StandardSpecification.createSpecificationWithHeaderOnly(getValue())
		);
	}
	
	//method
	/**
	 * @return the value of this textoid.
	 */
	public final String getValue() {
		return value;
	}
	
	//method
	/**
	 * @return the value of this textoid in quotes
	 */
	public final String getValueInQuotes() {
		
		final StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(CharacterCatalogue.APOSTROPH);
		stringBuilder.append(getValue());
		stringBuilder.append(CharacterCatalogue.APOSTROPH);
		
		return stringBuilder.toString();
	}	
	
	//method
	/**
	 * @param value
	 * @return true if this textoid has the given value.
	 */
	public final boolean hasValue(final String value) {
		return getValue().equals(value);
	}
}
