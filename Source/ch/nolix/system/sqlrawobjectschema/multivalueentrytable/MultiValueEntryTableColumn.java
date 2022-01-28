//package declaration
package ch.nolix.system.sqlrawobjectschema.multivalueentrytable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//enum
public enum MultiValueEntryTableColumn implements FullNamed {
	MULTI_VALUE_COLUMN_ID("MutliValueColumnId"),
	RECORD_ID("RecordId"),
	VALUE(PascalCaseCatalogue.VALUE);
	
	//constant
	private static final String NAME_PREFIX = MultiContentTable.MULTI_VALUE_ENTRY.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	MultiValueEntryTableColumn(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public final String getName() {
		return name;
	}
	
	//method
	@Override
	public String getNamePrefix() {
		return NAME_PREFIX;
	}
}
