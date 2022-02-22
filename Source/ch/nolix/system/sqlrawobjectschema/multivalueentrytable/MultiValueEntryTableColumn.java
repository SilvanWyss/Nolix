//package declaration
package ch.nolix.system.sqlrawobjectschema.multivalueentrytable;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//enum
public enum MultiValueEntryTableColumn implements FullNamed {
	MULTI_VALUE_COLUMN_ID("MutliValueColumnId"),
	ENTITY_ID("EntityId"),
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
