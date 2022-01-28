//package declaration
package ch.nolix.system.sqlrawobjectschema.structure;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum MultiContentTable implements FullNamed {
	MULTI_VALUE_ENTRY("MultiValueEntry"),
	MULTI_REFERENCE_ENTRY("MultiReferenceEntry"),
	MULTI_BACK_REFERENCE_ENTRY("MultiBackReferenceEntry");
	
	//constant
	private static final String NAME_PREFIX = TableType.MULTI_CONTENT_DATA.getNamePrefix();
	
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
	@Override
	public final String getNamePrefix() {
		return NAME_PREFIX;
	}
}
