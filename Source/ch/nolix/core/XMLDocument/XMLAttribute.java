//package declaration
package ch.nolix.core.XMLDocument;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.skillInterfaces.Valued;
import ch.nolix.primitive.validator2.Validator;

//class
public final class XMLAttribute
extends NamedElement
implements Valued<XMLAttribute, String> {
	
	//optional attribute
	private final String value;
	
	//constructor
	public XMLAttribute(final String name) {
		
		super(name);
		
		value = StringCatalogue.EMPTY_STRING;
	}
	
	//constructor
	public XMLAttribute(final String name, final String value) {
		
		super(name);
		
		Validator.suppose(value).isInstance();
		
		this.value = value;
	}
	
	//method
	public String getValue() {
		return value;
	}
	
	//method
	public String toString() {
		return (getName() + "='" + getValue() + "'");
	}
}
