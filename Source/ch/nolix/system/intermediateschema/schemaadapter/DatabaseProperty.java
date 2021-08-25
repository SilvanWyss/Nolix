//package declaration
package ch.nolix.system.intermediateschema.schemaadapter;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public enum DatabaseProperty {
	SCHEMA_TIMESTAMP("SchemaTimestamp");
	
	//attribute
	private final String label;
	
	//constructor
	DatabaseProperty(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	public String getLabel() {
		return label;
	}
}
