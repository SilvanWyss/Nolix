//package declaration
package ch.nolix.system.sqlrawschema.multivalueentrytable;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.QualifiedNamed;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;

//enum
public enum MultiValueEntryTableColumn implements QualifiedNamed {
	MULTI_VALUE_COLUMN_ID("MutliValueColumnId"),
	ENTITY_ID("EntityId"),
	VALUE(PascalCaseCatalogue.VALUE);
	
	//constant
	private static final String NAME_PREFIX = MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	MultiValueEntryTableColumn(final String name) {
		
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
