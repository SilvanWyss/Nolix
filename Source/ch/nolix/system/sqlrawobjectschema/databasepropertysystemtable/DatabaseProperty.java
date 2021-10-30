//package declaration
package ch.nolix.system.sqlrawobjectschema.databasepropertysystemtable;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Labeled;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

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
