//package declaration
package ch.nolix.system.sqlrawschema.structure;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.FullNamed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

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
