//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.core.attributeapi.mandatoryattributeapi.FullNamed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.sqlrawschema.structure.SystemDataTable;

//enum
public enum TableTableColumn implements FullNamed {
	ID(PascalCaseCatalogue.ID),
	NAME(PascalCaseCatalogue.NAME);
	
	//constant
	private static final String NAME_PREFIX = SystemDataTable.TABLE.getFullName() + StringCatalogue.DOT;
	
	//attribute
	private final String name;
	
	//constructor
	TableTableColumn(final String name) {
		
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
	public String getNamePrefix() {
		return NAME_PREFIX;
	}
}
