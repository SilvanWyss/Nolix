//package declaration
package ch.nolix.system.sqlrawschema.multireferenceentrytable;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.FullNamed;
import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;

//enum
public enum MultiReferenceEntryTableColumn implements FullNamed {
	MULTI_REFERENCE_COLUMN_ID("MutliReferenceColumnId"),
	ENTITY_ID("EntityId"),
	REFERENCED_ENTITY_ID("ReferencedEntityId");
	
	//constant
	private static final String NAME_PREFIX =
	MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	MultiReferenceEntryTableColumn(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
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
