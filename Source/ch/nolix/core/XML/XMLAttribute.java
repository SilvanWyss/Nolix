//package declaration
package ch.nolix.core.XML;

import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.attributeAPI.Valued;
//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;

//class
public final class XMLAttribute implements Named, Valued<XMLAttribute, String> {
	
	//optional attributes
	private final String name;
	private final String value;
	
	//constructor
	public XMLAttribute(final String name) {
		this(name, StringCatalogue.EMPTY_STRING);
	}
	
	//constructor
	public XMLAttribute(final String name, final String value) {
		this.name = Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();	
		this.value = Validator.suppose(value).thatIsNamed(VariableNameCatalogue.VALUE).isNotNull().andReturn();
	}
	
	//method
	@Override
	public String getName() {
		return name;
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
