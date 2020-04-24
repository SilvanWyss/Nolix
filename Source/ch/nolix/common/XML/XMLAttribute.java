//package declaration
package ch.nolix.common.XML;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.attributeAPI.Valued;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

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
		this.name = Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank().andReturn();	
		this.value = Validator.assertThat(value).thatIsNamed(VariableNameCatalogue.VALUE).isNotNull().andReturn();
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
