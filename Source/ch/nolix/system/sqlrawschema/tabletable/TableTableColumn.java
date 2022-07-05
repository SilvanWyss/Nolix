//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.FullNamed;
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
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
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
