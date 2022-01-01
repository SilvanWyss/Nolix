//package declaration
package ch.nolix.system.sqlrawobjectschema.structure;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum MultiContentTable implements Named {
	MULTI_VALUE_ENTRY("MultiValueEntryx"),
	MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
	MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");
	
	//attribute
	private final String name;
	
	//constructor
	MultiContentTable(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public final String getNameWithPrefix() {
		return (getPrefix() + getName());
	}
	
	//method
	public final String getPrefix() {
		return TableType.MULTI_CONTENT_DATA.getPrefix();
	}
}
