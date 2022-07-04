//package declaration
package ch.nolix.system.sqlrawschema.structure;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.FullNamed;

//enum
public enum SystemDataTable implements FullNamed {
	DATABASE_PROPERTY("DatabaseProperty"),
	TABLE(PascalCaseCatalogue.TABLE),
	COLUMN(PascalCaseCatalogue.COLUMN);
	
	//constant
	private static final String NAME_PREFIX = TableType.SYSTEM_DATA.getNamePrefix();
	
	//attribute
	private final String name;
	
	//constructor
	SystemDataTable(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public String getNamePrefix() {
		return NAME_PREFIX;
	}
}
