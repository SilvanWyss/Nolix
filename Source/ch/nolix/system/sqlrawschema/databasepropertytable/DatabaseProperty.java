//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

//own imports
import ch.nolix.core.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public enum DatabaseProperty implements Labeled {
	SCHEMA_TIMESTAMP("SchemaTimestamp");
	
	//attribute
	private final String label;
	
	//constructor
	DatabaseProperty(final String label) {
		
		Validator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
