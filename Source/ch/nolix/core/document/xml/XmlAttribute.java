//package declaration
package ch.nolix.core.document.xml;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlAttribute;

//class
public final class XmlAttribute implements IXmlAttribute {
	
	//optional attribute
	private final String name;
	
	//optional attribute
	private final String value;
	
	//constructor
	public XmlAttribute(final String name) {
		this(name, StringCatalogue.EMPTY_STRING);
	}
	
	//constructor
	public XmlAttribute(final String name, final String value) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
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