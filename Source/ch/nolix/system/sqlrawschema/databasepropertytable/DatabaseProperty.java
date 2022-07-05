//package declaration
package ch.nolix.system.sqlrawschema.databasepropertytable;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Labeled;

//class
public enum DatabaseProperty implements Labeled {
	SCHEMA_TIMESTAMP("SchemaTimestamp");
	
	//attribute
	private final String label;
	
	//constructor
	DatabaseProperty(final String label) {
		
		GlobalValidator.assertThat(label).thatIsNamed(LowerCaseCatalogue.LABEL).isNotBlank();
		
		this.label = label;
	}
	
	//method
	@Override
	public final String getLabel() {
		return label;
	}
}
