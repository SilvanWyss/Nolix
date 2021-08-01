//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum SQLTableType {
	SYSTEM_DATA("S"),
	CONTENT_DATA("C"),
	MULTI_PROPERTY_COLUMN_DATA("M");
	
	//attribute
	private final String prefix;
	
	//constructor
	SQLTableType(final String prefix) {
		
		Validator.assertThat(prefix).thatIsNamed(LowerCaseCatalogue.PREFIX).isNotBlank();
		
		this.prefix = prefix;
	}
	
	//method
	public final String getPrefix() {
		return prefix;
	}
}
