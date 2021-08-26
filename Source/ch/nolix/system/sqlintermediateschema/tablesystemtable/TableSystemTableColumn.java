//package declaration
package ch.nolix.system.sqlintermediateschema.tablesystemtable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum TableSystemTableColumn implements Named {
	NAME(PascalCaseCatalogue.NAME);
	
	//attribute
	private final String name;
	
	//constructor
	TableSystemTableColumn(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
