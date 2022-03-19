//package declaration
package ch.nolix.core.document.xml;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.attributeapi.mandatoryattributeapi.Valued;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

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
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		this.name = name;
		this.value = value;
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
