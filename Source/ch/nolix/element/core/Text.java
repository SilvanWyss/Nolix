//package declaration
package ch.nolix.element.core;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A text is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public class Text extends Element {

	//type name
	public static final String TYPE_NAME = "Text";
	
	//default value
	public static final String DEFAULT_VALUE = StringCatalogue.EMPTY_STRING;
	
	//static method
	/**
	 * @param specification
	 * @return a new text from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Text createFromSpecification(final Specification specification) {
		return new Text(specification.getOneAttributeAsString());
	}
	
	//attribute
	private final String value;
	
	//constructor
	/**
	 * Creates a new text with a default value.
	 */
	public Text() {
		
		//Calls other constructor
		this(DEFAULT_VALUE);
	}
	
	//constructor
	/**
	 * Creates a new text with the given value.
	 * 
	 * @param value
	 * @throws NullArgumentException if the given value is not an instance.
	 */
	public Text(final String value) {
		
		//Checks if the given value is an instance.
		Validator.suppose(value).thatIsNamed("value").isInstance();
		
		//Sets the value of this text.
		this.value = value;
	}
	
	//method
	/**
	 * @return the attributes of this text.
	 */
	public final List<StandardSpecification> getAttributes() {
		
		final var attributes = new List<StandardSpecification>();
		
		if (getValue().isEmpty()) {
			attributes.addAtEnd(new StandardSpecification());
		}
		else {
			attributes.addAtEnd(StandardSpecification.createSpecificationWithHeader(getValue()));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the value of this text.
	 */
	public final String getValue() {
		return value;
	}
	
	//method
	/**
	 * @return the value of this text in quotes
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
	 * @return true if this text has the given value.
	 */
	public final boolean hasValue(final String value) {
		return getValue().equals(value);
	}
}
