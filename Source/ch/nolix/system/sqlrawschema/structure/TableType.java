//package declaration
package ch.nolix.system.sqlrawschema.structure;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;

//enum
public enum TableType {
	SYSTEM_DATA("S"),
	BASE_CONTENT_DATA("B"),
	MULTI_CONTENT_DATA("M");
	
	//attribute
	private final String namePrefix;
	
	//constructor
	TableType(final String namePrefix) {
		
		Validator.assertThat(namePrefix).thatIsNamed("name prefix").isNotBlank();
		
		this.namePrefix = namePrefix;
	}
	
	//method
	public final String getNamePrefix() {
		return namePrefix;
	}
}
