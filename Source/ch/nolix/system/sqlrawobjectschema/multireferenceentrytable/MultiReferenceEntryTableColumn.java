//package declaration
package ch.nolix.system.sqlrawobjectschema.multireferenceentrytable;

import ch.nolix.core.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//enum
public enum MultiReferenceEntryTableColumn implements FullNamed {
	MULTI_REFERENCE_COLUMN_ID("MutliReferenceColumnId"),
	RECORD_ID("RecordId"),
	REFERENCED_RECORD_ID("ReferencedRecordId");
	
	//constant
	private static final String NAME_PREFIX =
	MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	MultiReferenceEntryTableColumn(final String name) {
		
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
