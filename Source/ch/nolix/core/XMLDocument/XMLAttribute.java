//package declaration
package ch.nolix.core.XMLDocument;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.skillAPI.Valued;
import ch.nolix.core.validator2.Validator;

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
		
		Validator.suppose(value).isNotNull();
		
		this.value = value;
	}
	
	//method
	@Override
	public String getValue() {
		return value;
	}
	
	//method
	@Override
	public String toString() {
		return (getName() + "='" + getValue() + "'");
	}
}
