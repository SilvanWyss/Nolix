//package declaration
package ch.nolix.system.sqlintermediateschema.schemaadapter;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum SystemDataTable implements Named {
	DATABASE_PROPERTY("DatabaseProperty"),
	TABLE(PascalCaseCatalogue.TABLE),
	COLUMN(PascalCaseCatalogue.COLUMN);
	
	//attribute
	private final String name;
	
	//constructor
	SystemDataTable(final String name) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		
		this.name = name;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	public String getNameWithPrefix() {
		return (TableType.SYSTEM_DATA + getName());
	}
}
